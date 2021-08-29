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
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  // TODO : KULLANICI BİR TEXT AREA'YA TEXT YAZSIN VE DOSYAYI BUTONA TIKLAYINCA .TXT OLARAK BİLGİSAYARINA İNDİREBİLSİN.
  private final static String FILE_NOT_FOUND_EXCEPTION = "File not found ";
  private final static String FILE_STORAGE_COULDNT_STORE_EXCEPTION = "File could not storage ";

  private final FileRepository fileRepository;

  @Autowired
  public FileStorageServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  public FileDB downloadFileById(String fileId) throws FileNotFoundException {
    return fileRepository.findById(fileId).orElseThrow(() ->
      new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION + fileId));
  }

  public Stream<FileDB> getAllFiles() {
    return fileRepository.findAll().stream();
  }

  @Override
  public FileDB storeFile(MultipartFile file) throws FileStorageException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    try {
      if (!(fileName.contains(".xlsx") || fileName.contains(".xls") || fileName.contains(".txt")) || fileName.isEmpty()) {
        throw new FileStorageException("Sorry ! File name contains invalid path sequence or your file is null: " + fileName);
      }
      FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), dtf.format(now));
      return fileRepository.save(fileDB);
    } catch (IOException ex) {
      throw new FileStorageException(FILE_STORAGE_COULDNT_STORE_EXCEPTION + fileName + "Please try again", ex);
    }
  }
}