package com.example.springbootbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication {


  private static final Logger logger = LoggerFactory.getLogger(SpringbootBackendApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringbootBackendApplication.class, args);

    logger.info("APPLICATION IS RUNNING");
  }


}
