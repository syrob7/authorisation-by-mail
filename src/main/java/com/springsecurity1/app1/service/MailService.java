package com.springsecurity1.app1.service;

import javax.mail.MessagingException;

public interface MailService {

    void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException;
}
