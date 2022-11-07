package com.example.springbootbackend.service.implemantations;

import com.example.springbootbackend.config.exception.SourceAlreadyExistsException;
import com.example.springbootbackend.config.exception.SourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.model.dto.EmployeeDTO;
import com.example.springbootbackend.repository.EmployeeRepository;
import com.example.springbootbackend.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class IEmployeeServiceImpl implements IEmployeeService {

  private final static String EMPLOYEE_NOT_FOUND_BY_ID = "Employee not found in the database with ID: ";
  private final static String EMPLOYEE_ALREADY_EXISTS = "Employee already exists in the database ";
  private final static String EMPLOYEE_NOT_FOUND_BY_FIRST_NAME = "First name not found in the database ";

  private final EmployeeRepository employeeRepository;
  private final EmailSenderService emailSenderService;
  private final ModelMapper modelMapper;
  private DateTimeFormatter dtf;


  @Autowired
  public IEmployeeServiceImpl(EmployeeRepository employeeRepository, EmailSenderService senderService, ModelMapper modelMapper) {
    this.employeeRepository = employeeRepository;
    this.emailSenderService = senderService;
    this.modelMapper = modelMapper;
  }

  @Override
  public ResponseEntity<Employee> firstNameSearching(String firstName) {
    Employee employeeByFirstName = employeeRepository.findByFirstNameContaining(firstName).orElseThrow(() ->
      new SourceNotFoundException(EMPLOYEE_NOT_FOUND_BY_FIRST_NAME));

    log.trace("Executing firstNameSearching [{}]", firstName);
    return ResponseEntity.ok(employeeByFirstName);

  }

  @Override
  public ResponseEntity<Map<String, Object>> getAllEmployees(int page, int size) {

    try {
      Pageable paging = PageRequest.of(page, size);

      Page<Employee> pageTuts;
      pageTuts = employeeRepository.findAll(paging);

      List<Employee> employees = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("employees", employees);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());

      log.trace("Executing getAllEmployees ,page : [{}], size : [{}] ", page, size);
      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (Exception ex) {
      log.warn("Not executed getAllEmployees [{}]", page, ex);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<Employee> getEmployeeByFirstName(String firstName) {
    Employee employeeByFirstName = employeeRepository.findByFirstName(firstName).orElseThrow(() ->
      new SourceNotFoundException(EMPLOYEE_NOT_FOUND_BY_FIRST_NAME + firstName));

    log.trace("Executing getEmployeeByFirstName [{}]", firstName);
    return ResponseEntity.ok(employeeByFirstName);
  }

  public ResponseEntity<Employee> getEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
      new SourceNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID + id));

    log.trace("Executing getEmployeeById [{}]", id);
    return ResponseEntity.ok(employee);
  }

  @Override
  public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
    Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());

    Employee employee = modelMapper.map(employeeDTO, Employee.class);

    LocalDateTime now = LocalDateTime.now();

    if (employeeOptional.isPresent()) {
      log.warn("[{}] [{}] already created , created time [{}], not executed createEmployee ", employee.getFirstName(), employee.getLastName(), employee.getCreatedTime());
      throw new SourceAlreadyExistsException(EMPLOYEE_ALREADY_EXISTS + " " + employee.getId() + " " + employee.getFirstName());
    }
    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setEmailID(employeeDTO.getEmailID());
    employee.setCreatedTime(dtf.format(now));
    employee.isUpdated();

    log.trace("[{}] [{}] created ", employeeDTO.getFirstName(), employeeDTO.getLastName());

    return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);


  }

  @Override
  public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
    Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());

    // entity -> dto via mapper api
    Employee employee = modelMapper.map(employeeDTO, Employee.class);

    if (employeeOptional.isPresent()) {
      log.warn("[{}] [{}] already created , created time [{}], not executed createEmployee ", employee.getFirstName(), employee.getLastName(), employee.getCreatedTime());
      throw new SourceAlreadyExistsException(EMPLOYEE_ALREADY_EXISTS + " " + employee.getId() + " " + employee.getFirstName());
    }

    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setEmailID(employeeDTO.getEmailID());
    employee.setUpdated(true);

    Employee updatedEmployee = employeeRepository.save(employee);

    emailSenderService.sendEmail(
      "aksuna.tunc@gmail.com",
      "UPDATE OPERATION - EMPLOYEE MANAGEMENT SYSTEM",
      employee.getFirstName() + " " + "has been successfully updated  !",
      new Date()
    );

    log.trace("Executing updateEmployee, employeeId : [{}], employee : [{}] and sent mail successfully", id, employeeDTO);

    return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);

  }

  @Override
  public ResponseEntity<Object> deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
      new SourceNotFoundException(EMPLOYEE_NOT_FOUND_BY_ID + id));

    employeeRepository.delete(employee);

    emailSenderService.sendEmail(
      "aksuna.tunc@gmail.com",
      "DELETE Operation - EMPLOYEE MANAGEMENT SYSTEM",
      employee.getFirstName() + " " + employee.getLastName() + " has been successfully deleted from the system." + " ID : " + employee.getId(),
      new Date()
    );

    log.trace("Executing deleteEmployee ,employeeId : [{}]", id);
    return ResponseEntity.ok("Employee successfully deleted");
  }

}































