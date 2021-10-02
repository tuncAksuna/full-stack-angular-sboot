package com.example.springbootbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
@Slf4j
public class EmailSenderService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail, String subjectOfMail, String bodyOfEmail, Date sentTime) {

    MimeMessage mimeMessage = mailSender.createMimeMessage();

    try {
      MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);

      mimeHelper.setFrom("aksuna.tunc@gmail.com");
      mimeHelper.setTo(toEmail);
      mimeHelper.setText(bodyOfEmail);
      mimeHelper.setSubject(subjectOfMail);
      mimeHelper.setSentDate(sentTime);

      FileSystemResource file = new FileSystemResource("C:\\Users\\netadmin\\Desktop\\SPRING and JAVA\\PROJELER\\full-stack-app\\BACKEND\\src\\main\\java\\assets\\employee-deleted.png");
      mimeHelper.addAttachment(file.getFilename(), file);
      log.trace("The mail succesfully sent to [{}]", toEmail);
      mailSender.send(mimeMessage);

    } catch (MessagingException ex) {
      log.warn("The mail has not been sent to [{}], sent time : [{}]", toEmail, sentTime);
      System.out.println(ex.getMessage());
    }
  }
}
