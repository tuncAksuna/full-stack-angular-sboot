package com.example.springbootbackend.service;

import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final FileRepository fileRepository;

  @Autowired
  public FileStorageServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  public FileDB store(MultipartFile file) throws IOException {

      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
      return fileRepository.save(fileDB);
  }

  public FileDB getFileById(String id) {
    return fileRepository.findById(id).get();
  }

  public Stream<FileDB> getAllFiles() {
    return fileRepository.findAll().stream();
  }
}
