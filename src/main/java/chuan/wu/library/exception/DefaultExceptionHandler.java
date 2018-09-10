/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.exception;

import chuan.wu.library.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public final ResponseEntity<ResultVO> handleCustomExceptions(GeneralException ex, WebRequest request) {
        ResultVO errorDetails = new ResultVO(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getCode()));
    }
}
