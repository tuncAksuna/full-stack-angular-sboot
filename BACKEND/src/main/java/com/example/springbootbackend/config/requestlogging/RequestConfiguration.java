package com.example.springbootbackend.config.requestlogging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RequestConfiguration implements WebMvcConfigurer {

  private final RequestInterceptor employeeRequestInterceptor;

  @Autowired
  public RequestConfiguration(RequestInterceptor employeeRequestInterceptor) {
    this.employeeRequestInterceptor = employeeRequestInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(employeeRequestInterceptor).addPathPatterns("api/v1/**/");
  }
}
