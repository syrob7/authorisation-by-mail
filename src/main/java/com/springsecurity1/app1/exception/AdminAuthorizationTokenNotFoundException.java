package com.springsecurity1.app1.exception;

public class AdminAuthorizationTokenNotFoundException extends RuntimeException {
    public AdminAuthorizationTokenNotFoundException(String token) {
        super("Authorization token with value: " + token + " was not found");
    }

}
