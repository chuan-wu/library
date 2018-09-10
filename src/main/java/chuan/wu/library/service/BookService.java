package chuan.wu.library.service;

import chuan.wu.library.enums.BookStatus;
import chuan.wu.library.enums.UserStatus;
import chuan.wu.library.exception.GeneralException;
import chuan.wu.library.model.Book;
import chuan.wu.library.model.BorrowRecord;
import chuan.wu.library.model.User;
import chuan.wu.library.repository.BookRepository;
import chuan.wu.library.repository.BorrowRecordRepository;
import chuan.wu.library.repository.UserRepository;
import chuan.wu.library.utils.StatusCode;
import chuan.wu.library.vo.BookVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BorrowRecordRepository borrowRecordRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookVO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookVO> bookVOS = books.stream().map(book -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            return bookVO;
        }).collect(Collectors.toList());
        return bookVOS;
    }

    /**
     * @param bookIds
     * @param userId
     */
    @Transactional
    public void borrowBook(List<Integer> bookIds, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, "User Does Not Exist");
        }
        User user = userOptional.get();
        if (user.getStatus() == UserStatus.DISABLED.getStatus()) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, "User is disabled");
        }
        for (Integer bookId : bookIds) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if (!bookOptional.isPresent()) {
                throw new GeneralException(StatusCode.PARAMETER_ERROR, "Book Does Not Exist");
            }

            Book book = bookOptional.get();
            if (book.getStatus() != BookStatus.AVAILABLE.getStatus()) {
                throw new GeneralException(StatusCode.PARAMETER_ERROR, "Book is unavailable");
            }

            // Set book status to borrowed
            book.setStatus(BookStatus.BORROWED.getStatus());
            bookRepository.save(book);

            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setBook(book);
            borrowRecord.setUser(user);
            borrowRecord.setDate(new Date());
            borrowRecordRepository.save(borrowRecord);
        }
    }
}
