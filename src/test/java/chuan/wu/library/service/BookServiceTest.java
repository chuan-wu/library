/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.service;

import chuan.wu.library.LibraryApplicationTests;
import chuan.wu.library.enums.BookStatus;
import chuan.wu.library.enums.UserStatus;
import chuan.wu.library.model.Book;
import chuan.wu.library.model.User;
import chuan.wu.library.repository.BookRepository;
import chuan.wu.library.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

public class BookServiceTest extends LibraryApplicationTests {
    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    private User user;
    private Book book;

    @Before
    public void before() {
        book = new Book();
        book.setAuthor("Jackie");
        book.setIsbn("aklektk");
        book.setTitle("Javascript essentials");
        book.setStatus(BookStatus.AVAILABLE.getStatus());
        bookRepository.save(book);

        user = new User();
        user.setUsername("Bob");
        user.setStatus(UserStatus.ACTIVE.getStatus());
        userRepository.save(user);
    }

    //    @After
    public void after() {
        bookRepository.delete(book);
        userRepository.delete(user);
    }

    @Test
    public void testBorrowBook() {
        Optional<User> userOptional = userRepository.findById(user.getId());
        bookService.borrowBook(Arrays.asList(book.getId()), user.getId());
    }


}