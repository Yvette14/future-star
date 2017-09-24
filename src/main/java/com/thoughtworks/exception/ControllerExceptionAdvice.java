package com.thoughtworks.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = InvalidCredentialException.class)
    public String handleSQLException(InvalidCredentialException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception) {
        return exception.getMessage();
    }


}