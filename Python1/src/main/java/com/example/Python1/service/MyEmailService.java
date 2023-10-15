package com.example.Python1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyEmailService  {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpMessage(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        LOG.info(subject);
        LOG.info(to);
        LOG.info(message);

        //Uncomment to send mail
        javaMailSender.send(simpleMailMessage);
    }
}
