package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/employees/search/{firstName}")
  public ResponseEntity<Employee> firstNameSearching(@PathVariable("firstName") String firstName) {
    return employeeService.firstNameSearching(firstName);
  }

  @GetMapping("/employees/{page}/{size}")
  public List<Employee> getAllEmployees(@PathVariable int page,
                                        @PathVariable int size) {

    return employeeService.getAllEmployees(page, size);
  }

  @GetMapping("/employees/firstName/{firstName}")
  public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable("firstName") String firstName) {

    return employeeService.getEmployeeByFirstName(firstName);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {

    return employeeService.getEmployeeById(id);
  }

  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee) {

    return employeeService.createEmployee(employee);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable @Max(2) Long id, @RequestBody Employee employee) {
    return employeeService.updateEmployee(id, employee);
  }

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
    return employeeService.deleteEmployee(id);
  }
}

