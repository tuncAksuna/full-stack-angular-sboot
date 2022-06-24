package com.example.springbootbackend.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {SourceNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleNotFoundException(SourceNotFoundException exc) {
    //PAYLOAD
    HttpStatus status = HttpStatus.NOT_FOUND;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.NOT_FOUND.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<>(errorResponse, status);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {SourceAlreadyExistsException.class})
  public ResponseEntity<ErrorResponse> handleAlreadyExistException(SourceAlreadyExistsException exc) {
    HttpStatus status = HttpStatus.CONFLICT;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.CONFLICT.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<>(errorResponse, status);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {FileStorageException.class})
  public ResponseEntity<ErrorResponse> handleFileStorageException(FileStorageException exc) {
    HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

    ErrorResponse errorResponse = new ErrorResponse(
      exc.getMessage(),
      HttpStatus.METHOD_NOT_ALLOWED.value(),
      ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<>(errorResponse, status);
  }

}
