package com.bank.service;

import com.bank.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public int sendMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("artem2549@gmail.com");
        message.setSubject("Verification Code");
        int code = VerificationCode.generate();
        message.setText(String.valueOf(code));
        try{
            mailSender.send(message);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
        return code;
    }

}
