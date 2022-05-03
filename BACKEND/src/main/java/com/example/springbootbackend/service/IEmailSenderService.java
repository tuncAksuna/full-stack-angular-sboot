package com.example.springbootbackend.service;

import java.util.Date;

public interface IEmailSenderService {

  void sendEmail(String toEmail, String subjectOfMail, String bodyOfEmail, Date sentTime);
}
