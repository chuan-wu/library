/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.service;

import chuan.wu.library.exception.GeneralException;
import chuan.wu.library.model.Book;
import chuan.wu.library.model.BorrowRecord;
import chuan.wu.library.model.User;
import chuan.wu.library.repository.BookRepository;
import chuan.wu.library.repository.BorrowRecordRepository;
import chuan.wu.library.repository.UserRepository;
import chuan.wu.library.utils.StatusCode;
import chuan.wu.library.vo.BookVO;
import chuan.wu.library.vo.BorrowRecordVO;
import chuan.wu.library.vo.UserVO;
import com.google.common.hash.Hashing;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BorrowRecordRepository borrowRecordRepository;

    public User getUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

    public void createUser(User user) {
        User existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new GeneralException(StatusCode.INTERNAL_ERROR, "User already exists");
        }

        String encrytpedPassword = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(encrytpedPassword);
        userRepository.save(user);
    }

    public List<UserVO> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserVO> userList = users.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return userList;
    }

    public BorrowRecordVO getUserBorrowList(Integer userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, "User does not exist");
        }
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findAllByUserId(userId);
        List<BookVO> books = borrowRecords.stream().map(borrowRecord -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(borrowRecord.getBook(), bookVO);
            return bookVO;
        }).collect(Collectors.toList());
        BorrowRecordVO borrowRecordVO = new BorrowRecordVO();
        borrowRecordVO.setUserId(userId);
        borrowRecordVO.setBorrowedBooks(books);
        return borrowRecordVO;
    }
}
