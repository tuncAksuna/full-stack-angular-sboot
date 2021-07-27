package com.example.springbootbackend.service;

import com.example.springbootbackend.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {

  Stream<FileDB> getAllFiles();

  FileDB getFileById(String id);

  FileDB store(MultipartFile file) throws IOException;
}
