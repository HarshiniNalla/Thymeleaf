package com.example.demo.services;

import com.example.demo.entity.MailUser;
import com.example.demo.repo.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public MailUser postData(MailUser user)
    {
        return userRepository.save(user);
    }

    public void sendEmails() {
        List<MailUser> users = userRepository.findAll();

        for (MailUser user : users) {
            String messageBody = "hello everyone ";
            try {
                sendEmail(user.getEmail(), "Hello", user.getName(), messageBody);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendEmail(String to, String subject, String name, String messageBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("message", messageBody);

        String htmlBody = templateEngine.process("emailTemplate", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }



}
