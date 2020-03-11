package com.springsecurity1.app1.service;

public interface TokenService<T> {

    T findByValue(String value);

    Long deleteByValue(String token);

    T save(T verificationToken);

}
