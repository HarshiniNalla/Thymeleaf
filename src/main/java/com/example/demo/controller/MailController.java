package com.example.demo.controller;

import com.example.demo.entity.MailUser;
import com.example.demo.repo.UserRepository;
import com.example.demo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class MailController {

    @Autowired
    private MailService emailService;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/send")
    public String sendEmails() {
        try {
            emailService.sendEmails();
            return "Emails sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send emails: " + e.getMessage();
        }
    }
@PostMapping("/postData")
    public MailUser postingData(@RequestBody MailUser mailUser)
{
    return emailService.postData(mailUser);
}
}
