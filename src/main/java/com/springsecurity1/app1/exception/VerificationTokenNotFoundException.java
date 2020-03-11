package com.springsecurity1.app1.exception;

public class VerificationTokenNotFoundException extends RuntimeException {

    public VerificationTokenNotFoundException(String value) {
        super("Token with value: " + value  +  " was not found in the db");
    }
}
