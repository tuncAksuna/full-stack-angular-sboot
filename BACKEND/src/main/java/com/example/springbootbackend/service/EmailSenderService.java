package com.example.springbootbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class EmailSenderService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail, String subjectOfMail, String bodyOfEmail, Date sentTime) {

    SimpleMailMessage mailMessage = new SimpleMailMessage();

    mailMessage.setFrom("aksuna.tunc@gmail.com");
    mailMessage.setTo(toEmail);
    mailMessage.setText(bodyOfEmail);
    mailMessage.setSubject(subjectOfMail);
    mailMessage.setSentDate(sentTime);

    try {
      log.trace("The mail succesfully sent to [{}]", toEmail);
      mailSender.send(mailMessage);
    } catch (MailException ex) {
      log.warn("The mail has not been sent to [{}], sent time : [{}]", toEmail, sentTime);
      System.out.println(ex.getMessage());
    }
  }
}
