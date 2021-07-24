package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.EmployeeAlreadyExistException;
import com.example.springbootbackend.config.exception.EmployeeNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final static String EMPLOYEE_NOT_FOUND_BY_ID = "Employee not found in the database";
  private final static String EMPLOYEE_ALREADY_EXISTS = "Employee already exists in the database ";
  private final static String EMPLOYEE_NOT_FOUND_BY_FIRST_NAME = "First name not found in the database";

  @Autowired
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }


  public ResponseEntity<Employee> firstNameSearching(String firstName) {
    Employee employeeByFirstName = employeeRepository.findByFirstNameContaining(firstName).orElseThrow(() ->
      new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_FIRST_NAME));

    return ResponseEntity.ok(employeeByFirstName);

  }

  public List<Employee> getAllEmployees(int page ,int size) {
    Pageable pageable = PageRequest.of(page,size);
    Page<Employee> employeePagination = employeeRepository.findAll(pageable);

    return employeePagination.toList();
  }

  public Page<Employee> getAllEmployeesPaginate(int page, int size) {
    return employeeRepository.findAll(PageRequest.of(page, size));
    // TODO : BU METOD DAHA SONRA YAPILACAKTIR PAGINATION İÇİN
  }

  public ResponseEntity<Employee> getEmployeeByFirstName(String firstName) {
    Employee employeeByFirstName = employeeRepository.findByFirstName(firstName).orElseThrow(() ->
      new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_FIRST_NAME));

    return ResponseEntity.ok(employeeByFirstName);
  }

  public ResponseEntity<Employee> getEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
      new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID));

    return ResponseEntity.ok(employee);
  }

  public Employee createEmployee(Employee employee) {
    Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

    if (employeeOptional.isPresent()) {
      throw new EmployeeAlreadyExistException(EMPLOYEE_ALREADY_EXISTS);
    }
    return employeeRepository.save(employee);

  }

  public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails) {
    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
      new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID));

    employee.setFirstName(employeeDetails.getFirstName());
    employee.setLastName(employeeDetails.getLastName());
    employee.setEmailID(employeeDetails.getEmailID());

    Employee updatedEmployee = employeeRepository.save(employee);
    return ResponseEntity.ok(updatedEmployee);
  }

  public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
      new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID));

    employeeRepository.delete(employee);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted ", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }


}































