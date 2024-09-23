package com.skysoft.krd.uber.services;

public interface EmailSenderService {

     void sendEmail(String to, String subject, String body);
    void sendEmail(String[] to, String subject, String body);
}
