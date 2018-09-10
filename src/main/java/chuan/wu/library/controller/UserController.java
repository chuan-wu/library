/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.controller;

import chuan.wu.library.exception.GeneralException;
import chuan.wu.library.form.BorrowBookForm;
import chuan.wu.library.form.UserForm;
import chuan.wu.library.model.User;
import chuan.wu.library.service.BookService;
import chuan.wu.library.service.UserService;
import chuan.wu.library.utils.StatusCode;
import chuan.wu.library.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResultVO createUser(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, "Invalid Parameters");
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.createUser(user);
        return ResultVO.success();
    }

    @GetMapping("/list")
    public ResultVO getAllUser() {
        return ResultVO.success(userService.getAllUsers());
    }

    @PostMapping("book/borrow")
    public ResultVO borrowBooks(@Valid @RequestBody BorrowBookForm borrowBookForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GeneralException(StatusCode.PARAMETER_ERROR, bindingResult.getAllErrors().toString());
        }
        bookService.borrowBook(borrowBookForm.getBookIds(), borrowBookForm.getUserId());
        return ResultVO.success();
    }

    @GetMapping("{userId}/book/list")
    public ResultVO getUserBorrowedBooks(@PathVariable Integer userId) {
        return ResultVO.success(userService.getUserBorrowList(userId));
    }
}
