package com.cpg.metier;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
 
    private JavaMailSender mailSender;
 
    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom("hamouchka7@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Work e-mail");
            messageHelper.setText(message);
            FileSystemResource file = new FileSystemResource(new File("c:/cpg.jpg"));
            messageHelper.addAttachment(file.getFilename(), file);

        };
        mailSender.send(messagePreparator);
        
    }
 
}