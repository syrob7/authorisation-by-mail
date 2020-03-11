package com.springsecurity1.app1.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestApi {

    @GetMapping("/forUser")
    public String sayHitoUser(){
        return "Hello User";
    }

    @GetMapping("/forAdmin")
    public String sayHitoAdmin(){
        return "Hello admin";
    }

    @GetMapping("/forAll")
    public String sayHitoAll(){
        return "Hello all";
    }

}
