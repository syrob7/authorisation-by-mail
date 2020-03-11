package com.springsecurity1.app1.service;

import com.springsecurity1.app1.entity.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void addNewUser(AppUser appUser, HttpServletRequest request);

    void verifyToken(String token);

    void grantAdminAccess(String token);

}
