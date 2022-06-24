package com.example.springbootbackend.config.exception;

public class SourceNotFoundException extends RuntimeException {

    public SourceNotFoundException(String message) {
        super(message);
    }

    public SourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
