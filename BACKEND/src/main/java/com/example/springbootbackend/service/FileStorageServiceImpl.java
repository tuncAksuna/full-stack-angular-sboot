package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.FileStorageException;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final FileRepository fileRepository;

  @Autowired
  public FileStorageServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  public FileDB getFileById(String fileId) throws FileNotFoundException {
    return fileRepository.findById(fileId).orElseThrow(() ->
      new FileNotFoundException("File not found with id : " + fileId));
  }

  @Override
  public FileDB storeFile(MultipartFile file) throws FileStorageException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      if (fileName.contains(".xlsx") || file.isEmpty()) {
        throw new FileStorageException("Sorry ! File name contains invalid path sequence , your file or your file is null: " + fileName);

      }
      FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
      return fileRepository.save(fileDB);
    } catch (IOException ex) {
      throw new FileStorageException("Couldn't store your file " + fileName + "Please try again", ex);
    }
  }
}
