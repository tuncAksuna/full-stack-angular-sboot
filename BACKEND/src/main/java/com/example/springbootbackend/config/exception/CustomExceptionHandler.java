package com.example.springbootbackend.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = {EmployeeNotFoundException.class})
  public ResponseEntity<Object> handleNotFoundException(EmployeeNotFoundException exc) {
    //PAYLOAD
    HttpStatus status = HttpStatus.NOT_FOUND;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.NOT_FOUND.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<Object>(errorResponse, status);
  }

  @ExceptionHandler(value = {EmployeeAlreadyExistException.class})
  public ResponseEntity<Object> handleAlreadyExistException(EmployeeAlreadyExistException exc) {
    HttpStatus status = HttpStatus.CONFLICT;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.CONFLICT.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<Object>(errorResponse, status);
  }

  @ExceptionHandler(value = {FileStorageException.class})
  public ResponseEntity<Object> handleFileStorageException(FileStorageException exc) {
    HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.METHOD_NOT_ALLOWED.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<Object>(errorResponse, status);
  }

}
