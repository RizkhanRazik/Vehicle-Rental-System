package com.example.rentalclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Email implements EmailUtil {

    @Autowired
    private JavaMailSender sender;
    @Override
    public void SendEmail(String toAddress, String subject, String body) {
        MimeMessage mimeMailMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);

        try {
            mimeMessageHelper.setTo(toAddress);
        } catch (MessagingException e) {
            System.out.println("address");
            e.printStackTrace();
        }
        try {
            mimeMessageHelper.setSubject(subject);
        } catch (MessagingException e) {
            System.out.println("subject");
            e.printStackTrace();
        }
        try {
            mimeMessageHelper.setText(body);
        } catch (MessagingException e) {
            System.out.println("body");
            e.printStackTrace();
        }

        sender.send(mimeMailMessage);
    }
}
