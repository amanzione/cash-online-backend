package com.cashonline.examenbackend.controller;

import com.cashonline.examenbackend.controller.errors.HttpError;
import com.cashonline.examenbackend.exception.UserNotFoundException;
import com.cashonline.examenbackend.exception.UserValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserValidationException.class)
    public HttpError handleException(UserValidationException exception){
        HttpError error = new HttpError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public HttpError handleException(UserNotFoundException exception){
        HttpError error = new HttpError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public HttpError handleException(IllegalArgumentException exception){
        HttpError error = new HttpError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return error;
    }
}
