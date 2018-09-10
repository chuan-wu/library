/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.controller;

import chuan.wu.library.exception.GeneralException;
import chuan.wu.library.form.BookForm;
import chuan.wu.library.model.Book;
import chuan.wu.library.service.BookService;
import chuan.wu.library.utils.StatusCode;
import chuan.wu.library.vo.BookVO;
import chuan.wu.library.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/list")
    public ResultVO getAllBooks() {
        List<BookVO> books = bookService.getAllBooks();
        return ResultVO.success(books);
    }

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody BookForm bookForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, "Invalid Parameters");
        }

        Book book = new Book();
        BeanUtils.copyProperties(bookForm, book);
        bookService.addBook(book);
        return ResultVO.success();
    }

}
