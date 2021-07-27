package com.example.springbootbackend.controller;

import com.example.springbootbackend.config.filemessage.ResponseFile;
import com.example.springbootbackend.config.filemessage.ResponseMessage;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.service.EmployeeServiceImpl;
import com.example.springbootbackend.service.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class EmployeeController {

  private final EmployeeServiceImpl employeeServiceImpl;

  @Autowired
  public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
    this.employeeServiceImpl = employeeServiceImpl;
  }


  @GetMapping("/employees/search/{firstName}")
  public ResponseEntity<Employee> firstNameSearching(@PathVariable("firstName") String firstName) {
    return employeeServiceImpl.firstNameSearching(firstName);
  }

  @GetMapping("/employees/{page}/{size}")
  public List<Employee> getAllEmployees(@PathVariable int page,
                                        @PathVariable int size) {

    return employeeServiceImpl.getAllEmployees(page, size);
  }

  @GetMapping("/employees/firstName/{firstName}")
  public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable("firstName") String firstName) {
    return employeeServiceImpl.getEmployeeByFirstName(firstName);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {

    return employeeServiceImpl.getEmployeeById(id);
  }

  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee) {

    return employeeServiceImpl.createEmployee(employee);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable @Max(2) Long id, @RequestBody Employee employee) {
    return employeeServiceImpl.updateEmployee(id, employee);
  }

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
    return employeeServiceImpl.deleteEmployee(id);
  }
}
