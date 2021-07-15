package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    ResponseEntity<Employee> firstNameSearching(String firstName);

    List<Employee> getAllEmployees();

    Page<Employee> getAllEmployeesPaginate(int page,int size);
    // TODO : BU METOD DAHA SONRA YAPILACAKTIR PAGINATION İÇİN

    ResponseEntity<Employee> getEmployeeByFirstName(String firstName);

    ResponseEntity<Employee> getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    ResponseEntity<Employee> updateEmployee(Long id,Employee employeeDetails);

    ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);

}
