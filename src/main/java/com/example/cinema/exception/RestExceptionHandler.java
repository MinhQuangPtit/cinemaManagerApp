package com.example.cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        // Tạo một đối tượng chứa thông báo lỗi
        ErrorMessage error = new ErrorMessage(errorMessage);
        // Trả về ResponseEntity với mã lỗi và thông báo lỗi
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
