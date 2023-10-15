package com.example.Python1.controller;

import com.example.Python1.service.MyEmailService;
import com.example.Python1.service.OtpService;
import com.example.Python1.utility.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OtpController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public OtpService otpService;

    @Autowired
    public MyEmailService myEmailService;

    @GetMapping("/generateOtp")
    public String generateOtp(@RequestParam String email){

        String username = email;

        int otp = otpService.generateOTP(username);

        LOG.info("OTP : "+otp);

        //Generate The Template to send OTP
//        EmailTemplate template = new EmailTemplate("OtpSent.txt");
//
//        Map<String,String> replacements = new HashMap<String,String>();
//        replacements.put("user", username);
//        replacements.put("otpnum", String.valueOf(otp));
//
//        String message = template.getTemplate(replacements);
        String message = "The OTP for the user "+username+" is : "+otp;
        myEmailService.sendOtpMessage(username,"OTP for user registration", message);

        return "OTP sent successfully to email id : "+username;
    }

    @GetMapping(value ="/validateOtp")
    public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum, @RequestParam("username") String email){

        final String SUCCESS = "Entered Otp is valid";

        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

        String username = email;

        LOG.info(" Otp Number : "+otpnum);

        //Validate the Otp
        if(otpnum >= 0){
            int serverOtp = otpService.getOtp(username);

            if(serverOtp > 0){
                if(otpnum == serverOtp){
                    otpService.clearOTP(username);
                    return ("Entered Otp is valid");
                }else{
                    return SUCCESS;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }
}
