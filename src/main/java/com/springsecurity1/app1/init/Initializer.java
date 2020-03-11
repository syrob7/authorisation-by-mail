package com.springsecurity1.app1.init;

import com.springsecurity1.app1.entity.AppUser;
import com.springsecurity1.app1.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class Initializer {

    private PasswordEncoder passwordEncoder;
    private AppUserRepo appUserRepo;

    @Autowired
    public Initializer(PasswordEncoder passwordEncoder, AppUserRepo appUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepo = appUserRepo;
        addNewUser();
    }

    private void addNewUser(){
        AppUser appUser = new AppUser("admin@gmail.com", passwordEncoder.encode("admin"), true, "ROLE_ADMIN");
        appUser.setUsername("admin@gmail.com");
        appUserRepo.save(appUser);
    }
}
