package com.springsecurity1.app1.service.impl;

import com.springsecurity1.app1.entity.AdminAuthorizationToken;
import com.springsecurity1.app1.exception.AdminAuthorizationTokenNotFoundException;
import com.springsecurity1.app1.repo.AdminAuthorizationTokenRepo;
import com.springsecurity1.app1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "admin")
public class AdminAuthorizationTokenServiceImpl implements TokenService<AdminAuthorizationToken> {

    private AdminAuthorizationTokenRepo adminAuthorizationTokenRepo;

    @Autowired
    public AdminAuthorizationTokenServiceImpl(AdminAuthorizationTokenRepo adminAuthorizationTokenRepo) {
        this.adminAuthorizationTokenRepo = adminAuthorizationTokenRepo;
    }

    @Override
    public AdminAuthorizationToken findByValue(String token) {
        return adminAuthorizationTokenRepo.findByValue(token).orElseThrow(()-> new AdminAuthorizationTokenNotFoundException(token));
    }

    @Transactional
    @Override
    public Long deleteByValue(String token) {
        return adminAuthorizationTokenRepo.deleteByValue(token);
    }

    @Override
    public AdminAuthorizationToken save(AdminAuthorizationToken adminAuthorizationToken) {
        return adminAuthorizationTokenRepo.save(adminAuthorizationToken);
    }

}
