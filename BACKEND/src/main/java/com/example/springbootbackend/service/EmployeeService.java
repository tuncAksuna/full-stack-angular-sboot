package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

  ResponseEntity<Employee> firstNameSearching(String firstName);

  List<Employee> getAllEmployees(int page , int size);

  ResponseEntity<Employee> getEmployeeByFirstName(String firstName);

  ResponseEntity<Employee> getEmployeeById(Long id);

  Employee createEmployee(Employee employee);

  ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails);

  ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);


}
