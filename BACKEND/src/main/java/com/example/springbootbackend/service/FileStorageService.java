package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.FileStorageException;
import com.example.springbootbackend.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileStorageService {

  FileDB getFileById(String id) throws FileNotFoundException;


  FileDB storeFile(MultipartFile file) throws FileStorageException;
}
