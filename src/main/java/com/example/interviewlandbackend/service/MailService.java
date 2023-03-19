package com.example.interviewlandbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {


    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(String userEmail, String link , String contentName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("gurbanalizada011@gmail.com", "Gurban Alizada");
        helper.setTo(userEmail);

        String subject = "Interview Land";


        String content = "<p>Salam,</p>"
                + "<p>Abune oldugunuz \"" +contentName  + "\" contente yeni bir sual elave olundu</p>"
                + "<p>Asagidaki link vasitesi ile suala baxa bilersiniz</p>"
                + "<p><a href=\"" + link + "\">Interview sualina get</a></p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
