package com.example.rentalclub.service;

public interface EmailUtil {
    void SendEmail(String toAddress, String subject, String body);
}
