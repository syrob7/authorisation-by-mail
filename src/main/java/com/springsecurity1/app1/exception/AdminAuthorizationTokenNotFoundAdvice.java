package com.springsecurity1.app1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminAuthorizationTokenNotFoundAdvice {

    @ExceptionHandler(AdminAuthorizationTokenNotFoundException.class)
    public ResponseEntity<?> AdminAuthorizationTokenNotFoundHandler(AdminAuthorizationTokenNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
