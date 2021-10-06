package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.FileStorageException;
import com.example.springbootbackend.model.FileDB;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.stream.Stream;

public interface FileStorageService {

  FileDB downloadFileById(String id) throws FileNotFoundException;

  FileDB storeFile(MultipartFile file) throws FileStorageException;

  ResponseEntity<Map<String, Object>> getAllFiles(int page, int size);
  //Stream<FileDB> getAllFiles();

  Stream<FileDB> getAllFilesOrderBySizeASC();

  Stream<FileDB> getAllFilesOrderBySizeDESC();

  Stream<FileDB> getAllFilesOrderByUploadedTimeASC();

  Stream<FileDB> getAllFilesOrderByUploadedTimeDESC();


}
