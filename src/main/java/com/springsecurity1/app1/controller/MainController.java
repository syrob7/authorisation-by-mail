package com.springsecurity1.app1.controller;

import com.springsecurity1.app1.entity.AppUser;
import com.springsecurity1.app1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("registration", "user", new AppUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(AppUser user, HttpServletRequest request){
        userService.addNewUser(user, request);
        return new ModelAndView("redirect:/login");
    }

    //check user authorization token
    @RequestMapping("/verifyToken")
    public ModelAndView verifyToken(@RequestParam String token){
        userService.verifyToken(token);
        return new ModelAndView("redirect:/tokenVerified");
    }

    //check admin authorization token
    @RequestMapping("/grantAdminAccess")
    public ModelAndView grantAdminAccess(@RequestParam String token){
        userService.grantAdminAccess(token);
        return new ModelAndView("redirect:/adminAccessEnabled");
    }

    @RequestMapping("/adminAccessEnabled")
    @ResponseBody
    private String adminAccessEnabled(){ return "You have succesfully granted admin permissions"; }

    @RequestMapping("/tokenVerified")
    @ResponseBody
    private String tokenVerified(){ return "Token verification successfull"; }


}
