package com.example.springbootbackend.model;

import com.example.springbootbackend.config.validation.UniqueEmail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "{firstName.notempty}")
  @Size(max = 20)
  @Column(name = "first_name")
  private String firstName;

  @NotEmpty(message = "{lastName.notempty}")
  @Column(name = "last_name")
  private String lastName;

  @NotBlank
  @Email
  @UniqueEmail(message = "{emailID.unique}")
  @Column(name = "email_id", nullable = false)
  private String emailID;

  private String createdTime;

  private boolean isUpdated;

  public Employee(String firstName, String lastName, String emailID, String createdTime, boolean isUpdated) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailID = emailID;
    this.createdTime = createdTime;
    this.isUpdated = isUpdated;
  }

}
