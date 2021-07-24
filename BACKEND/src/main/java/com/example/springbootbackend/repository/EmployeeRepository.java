package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Employee findByEmailID(String emailID);

  Optional<Employee> findByFirstName(String firstName);

  Optional<Employee> findByFirstNameContaining(String firstName);


}
