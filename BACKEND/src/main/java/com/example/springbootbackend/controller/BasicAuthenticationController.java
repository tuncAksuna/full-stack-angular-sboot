package com.example.springbootbackend.controller;

import com.example.springbootbackend.security.AuthenticationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BasicAuthenticationController {

  private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationController.class);


  public AuthenticationBean basicAuthentication() {
    logger.info("AUTHENTICATON IS SUCCESSFULL");
    return new AuthenticationBean("YOU ARE SUCCESSFULLY AUTHENTICATED");
  }
}
