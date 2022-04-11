package com.cygnet.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService
{
    @Autowired
    private JavaMailSender javaMailSender;



    public void sendEmail(String toEmail, String body, String subject)
    {
        var message = new  SimpleMailMessage();

        message.setFrom("samroman789@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

    }
}
