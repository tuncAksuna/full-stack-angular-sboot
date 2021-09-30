package com.example.springbootbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail, String subjectOfMail, String bodyOfEmail) {

    SimpleMailMessage mailMessage = new SimpleMailMessage();

    mailMessage.setTo(toEmail);
    mailMessage.setText(bodyOfEmail);
    mailMessage.setSubject(subjectOfMail);

    log.trace("THE MAIL IS BEING SENT SUCCESSFULLY..");
    mailSender.send(mailMessage);

  }
}
