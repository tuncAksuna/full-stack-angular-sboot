package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

  ResponseEntity<Employee> firstNameSearching(String firstName);

  List<Employee> getAllEmployees(int page, int size);

  ResponseEntity<Employee> getEmployeeByFirstName(String firstName);

  ResponseEntity<Employee> getEmployeeById(Long id);

  Employee createEmployee(Employee employee);

  ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails);

  ResponseEntity<Object> deleteEmployee(Long id);


}
