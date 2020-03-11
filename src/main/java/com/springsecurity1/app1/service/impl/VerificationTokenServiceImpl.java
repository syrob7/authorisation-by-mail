package com.springsecurity1.app1.service.impl;

import com.springsecurity1.app1.entity.VerificationToken;
import com.springsecurity1.app1.exception.VerificationTokenNotFoundException;
import com.springsecurity1.app1.repo.VerificationTokenRepo;
import com.springsecurity1.app1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "verification")
public class VerificationTokenServiceImpl implements TokenService<VerificationToken> {

    private VerificationTokenRepo verificationTokenRepo;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepo verificationTokenRepo) {
        this.verificationTokenRepo = verificationTokenRepo;
    }

    @Override
    public VerificationToken findByValue(String value) {
        return verificationTokenRepo.findByValue(value).orElseThrow(()-> new VerificationTokenNotFoundException(value));
    }

    @Transactional
    @Override
    public Long deleteByValue(String token) {
        return verificationTokenRepo.deleteByValue(token);
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepo.save(verificationToken);
    }

}
