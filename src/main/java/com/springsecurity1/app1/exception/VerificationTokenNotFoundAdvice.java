package com.springsecurity1.app1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VerificationTokenNotFoundAdvice {

    @ExceptionHandler(VerificationTokenNotFoundException.class)
    public ResponseEntity<?> VerificationTokenNotFoundHandler(VerificationTokenNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

