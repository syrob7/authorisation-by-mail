package com.springsecurity1.app1.service.impl;

import com.springsecurity1.app1.entity.AdminAuthorizationToken;
import com.springsecurity1.app1.entity.AppUser;
import com.springsecurity1.app1.entity.VerificationToken;
import com.springsecurity1.app1.repo.AppUserRepo;
import com.springsecurity1.app1.service.MailService;
import com.springsecurity1.app1.service.TokenService;
import com.springsecurity1.app1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    private MailService mailService;
    private TokenService adminAuthorizationTokenService;
    private TokenService verificationTokenService;

    @Autowired
    public UserServiceImpl(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder,
                           MailService mailService,
                           @Qualifier("admin") TokenService adminAuthorizationTokenService,
                           @Qualifier("verification") TokenService verificationTokenService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.adminAuthorizationTokenService = adminAuthorizationTokenService;
        this.verificationTokenService = verificationTokenService;
    }

    @Value("${spring.mail.username}")
    private String superAdmin;


    public void addNewUser(AppUser appUser, HttpServletRequest request){

        //add new user
        appUser.setAuthority("ROLE_USER");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepo.save(appUser);

        //generate verification token
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken( token, appUser);
        verificationTokenService.save(verificationToken);

        //send token to user
        try {
            String url = "http://" + request.getServerName() + ":"+request.getServerPort() + request.getContextPath()+ "/verifyToken?token="+token;
            mailService.sendMail(appUser.getUsername(),"Token verification", url,false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //if user choose admin role then send to SuperAdmin authorization token message
        sendRequestTokenToAdmin(appUser, request);
    }

    //check if token from db matches user token from email
    public void verifyToken(String token){

        AppUser appUser = ((VerificationToken)verificationTokenService.findByValue(token)).getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        //delete used token from db
         verificationTokenService.deleteByValue(token);
    }

    //send admin authorization token to superAdmin
    private void sendRequestTokenToAdmin(AppUser appUser, HttpServletRequest request){

        if (appUser.getChosenRole().equals("ADMIN")){
            String token = UUID.randomUUID().toString();
            AdminAuthorizationToken adminAuthorizationToken = new AdminAuthorizationToken(token, appUser);
            adminAuthorizationTokenService.save(adminAuthorizationToken);
            String adminUrl = "http://" + request.getServerName() + ":"+request.getServerPort() + request.getContextPath()+ "/grantAdminAccess?token="+token;

            try {
                mailService.sendMail(superAdmin, "GRANT ADMIN AUTHORISATION", "User: " + appUser.getUsername() + " wants to get admin authority. \nActivation link: "+ adminUrl, false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    //check if admin authorization token from db matches token which was send to SuperAdmin
    public void grantAdminAccess(String token) {
        AppUser appUser = ((AdminAuthorizationToken)adminAuthorizationTokenService.findByValue(token)).getAppUser();
        appUser.setAuthority("ROLE_ADMIN");
        appUserRepo.save(appUser);
        //delete admin authorization token at the end
        adminAuthorizationTokenService.deleteByValue(token);
    }
}
