package com.springsecurity1.app1.service.impl;

import com.springsecurity1.app1.entity.AppUser;
import com.springsecurity1.app1.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private  AppUserRepo appUserRepo;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AppUser appUser = appUserRepo.findAllByUsername(s).orElseThrow(()-> new UsernameNotFoundException("User: " + s + " not found"));

        return appUser;
    }
}
