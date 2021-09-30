package com.example.springbootbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootApplication
public class SpringbootBackendApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringbootBackendApplication.class);

  @Autowired
  private EmailSenderService senderService;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootBackendApplication.class, args);
    logger.info("APPLICATION IS RUNNING");
  }

  @EventListener(ApplicationReadyEvent.class)
  public void sendEmail() {
    long milis = System.currentTimeMillis();
    Date sentMessageData = new Date(milis);

    senderService.sendEmail(
      "aksuna.tunc@gmail.com",
      "Subject of sent email,SMTP Implementation for Employee Management System",
      "This is a SMTP implementation for my special app, Employee Management System, also this is body of the sent message",
      sentMessageData);
  }
}
