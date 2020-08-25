package com.example.springrestotot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class QuoteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(QuoteNotFoundException ex) {
        return ex.getMessage();
    }
}
