package com.example.springbootbackend.model.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String emailID;
  private String createdTime;
  private boolean isUpdated;

}
