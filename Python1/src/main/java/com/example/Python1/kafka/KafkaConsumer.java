package com.example.Python1.kafka;

import com.example.Python1.controller.OtpController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    OtpController otpController;
    @KafkaListener(topics="email_registration", groupId="my_group_id")
    public void getMessage(String message){
        otpController.generateOtp(message);
        System.out.println("An otp has been send to "+message);

    }
}