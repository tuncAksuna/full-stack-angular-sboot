package com.example.springbootbackend;

import com.example.springbootbackend.service.implemantations.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootBackendApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringbootBackendApplication.class);

  @Autowired
  private EmailSenderService senderService;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootBackendApplication.class, args);
    logger.info("APPLICATION IS RUNNING");
  }

}
