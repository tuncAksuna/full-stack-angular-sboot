package com.example.springbootbackend.service;

import com.example.springbootbackend.config.exception.FileStorageException;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@Slf4j
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
    log.trace("Executing downloadFileById , fileId [{}]", fileId);
    return fileRepository.findById(fileId).orElseThrow(() ->
      new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION + fileId));
  }

  public ResponseEntity<Map<String, Object>> getAllFiles(int page, int size) {
    log.trace("Executing getAllFiles");

    try {
      Pageable paging = PageRequest.of(page, size);
      Page<FileDB> pageTuts;
      pageTuts = fileRepository.findAll(paging);

      List<FileDB> allFiles = pageTuts.getContent();

      Map<String, Object> resp = new HashMap<>();
      resp.put("files", allFiles);
      resp.put("currentPage", pageTuts.getNumber());
      resp.put("totalItems", pageTuts.getTotalElements());
      resp.put("totalPages", pageTuts.getTotalPages());

      log.trace("Executing getAllFiles , page [{}] , size [{}]", page, size);
      return new ResponseEntity<>(resp, HttpStatus.OK);

    } catch (Exception ex) {
      log.warn("Not executed getAllFiles");
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Stream<FileDB> getAllFilesOrderBySizeASC() {
    log.trace("Executing getAllFilesOrderBySizeASC");
    return fileRepository.findAll(Sort.by("data").ascending()).stream();
  }

  @Override
  public Stream<FileDB> getAllFilesOrderBySizeDESC() {
    log.trace("Executing getAllFilesOrderBySizeDESC");
    return fileRepository.findAll(Sort.by("data").descending()).stream();
  }

  @Override
  public Stream<FileDB> getAllFilesOrderByUploadedTimeASC() {
    log.trace("Executing getAllFilesOrderByUploadedTimeASC");
    return fileRepository.findAll(Sort.by("uploadedTime").ascending()).stream();
  }

  @Override
  public Stream<FileDB> getAllFilesOrderByUploadedTimeDESC() {
    log.trace("Executing getAllFilesOrderByUploadedTimeDESC");
    return fileRepository.findAll(Sort.by("uploadedTime").descending()).stream();
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
      log.trace("Executing storeFile, file [{}]", file.getOriginalFilename());
      return fileRepository.save(fileDB);
    } catch (IOException ex) {
      log.warn("Not executed storeFile, may be file doesn't contain .xls or .xlsx extension or fileName is empty,file [{}]", file.getOriginalFilename());
      throw new FileStorageException(FILE_STORAGE_COULDNT_STORE_EXCEPTION + fileName + "Please try again", ex);
    }
  }


}
