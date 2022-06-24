package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(value = "*")
@Slf4j
public class EmployeeController {

  private final IEmployeeService IEmployeeService;

  @Autowired
  public EmployeeController(IEmployeeService IEmployeeService) {
    this.IEmployeeService = IEmployeeService;
  }

  @GetMapping("/employees/search/{firstName}")
  public ResponseEntity<Employee> firstNameSearching(@PathVariable("firstName") String firstName) {
    return IEmployeeService.firstNameSearching(firstName);
  }


  @GetMapping("/employees")
  public ResponseEntity<Map<String, Object>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
    return IEmployeeService.getAllEmployees(page, size);
  }

  @GetMapping("/employees/firstName/{firstName}")
  public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable("firstName") String firstName) {
    return IEmployeeService.getEmployeeByFirstName(firstName);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {

    return IEmployeeService.getEmployeeById(id);
  }

  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee) {

    return IEmployeeService.createEmployee(employee);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable @Max(2) Long id, @RequestBody Employee employee) {
    return IEmployeeService.updateEmployee(id, employee);
  }

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
    return IEmployeeService.deleteEmployee(id);
  }
}

