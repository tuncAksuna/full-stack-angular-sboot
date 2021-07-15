package com.example.springbootbackend.config.validation;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {

        Employee employeeRepositoryByEmailID = employeeRepository.findByEmailID(val);

        if (employeeRepositoryByEmailID == null) {
            return true;
        }
        return false;

    }
}
