package com.example.springbootbackend.model;

import com.example.springbootbackend.config.validation.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employees")
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

    public Employee(String firstName, String lastName, String emailID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailID='" + emailID + '\'' +
                '}';
    }
}
