package com.safetynet.alerts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCatcher {

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String msg) {
            super(msg);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GlobalCatcher.BadRequestException.class)
    public String handleBadRequestException(BadRequestException e) {
        return e.getMessage();
    }
}
