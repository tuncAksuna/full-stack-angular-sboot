package com.example.springbootbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringbootBackendApplication  {

  private static final Logger logger = LoggerFactory.getLogger(SpringbootBackendApplication.class);

  @Autowired
  private EmailSenderService senderService;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootBackendApplication.class, args);
    logger.info("APPLICATION IS RUNNING");
  }

  @EventListener(ApplicationReadyEvent.class)
  public void sendEmail() {
    logger.info("The mail is being sent");
    senderService.sendEmail("aksuna.tunc@gmail.com", "KİLİS PROJESİ ÜZERİNE", "This is body of mail");
  }

}
