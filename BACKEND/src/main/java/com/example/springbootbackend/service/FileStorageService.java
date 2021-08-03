package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.FileStorageException;
import com.example.springbootbackend.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public interface FileStorageService {

  FileDB downloadFileById(String id) throws FileNotFoundException;

  FileDB storeFile(MultipartFile file) throws FileStorageException;

  Stream<FileDB> getAllFiles();
}
