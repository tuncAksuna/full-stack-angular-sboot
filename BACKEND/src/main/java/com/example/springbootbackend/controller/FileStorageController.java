package com.example.springbootbackend.controller;

import com.example.springbootbackend.config.filemessage.UploadFileResponse;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/transactions")
@CrossOrigin
public class FileStorageController {

  private final FileStorageService fileStorageService;

  @Autowired
  public FileStorageController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @GetMapping("/files/download/{fileId}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) throws FileNotFoundException {

    FileDB fileDB = fileStorageService.downloadFileById(fileId);

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(fileDB.getType()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(new ByteArrayResource(fileDB.getData()));
  }

  @GetMapping("/files/downloadAllFiles")
  public Stream<FileDB> getAllFiles() {
    return fileStorageService.getAllFiles();
  }

  @GetMapping("files/downloadOrderByDataASC")
  public Stream<FileDB> getAllFilesOrderBySizeASC() {
    return fileStorageService.getAllFilesOrderBySizeASC();
  }

  @GetMapping("files/downloadOrderByDataDESC")
  public Stream<FileDB> getAllFilesOrderBySizeDESC() {
    return fileStorageService.getAllFilesOrderBySizeDESC();
  }

  @GetMapping("files/downloadOrderByUploadedTimeASC")
  public Stream<FileDB> getAllFilesOrderByUploadedTimeASC(){
    return fileStorageService.getAllFilesOrderByUploadedTimeASC();
  }

  @GetMapping("files/downloadOrderByUploadedTimeDESC")
  public Stream<FileDB> getAllFilesOrderByUploadedTimeDESC(){
    return fileStorageService.getAllFilesOrderByUploadedTimeDESC();
  }

  @PostMapping("/files/upload")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    FileDB fileDB = fileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/downloadFile/")
      .path(fileDB.getId())
      .toUriString();

    return new UploadFileResponse(fileDB.getName(), fileDownloadUri, file.getContentType(), file.getSize(), fileDB.getUploadedTime());
  }

  @PostMapping("/files/uploadMultiple")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files)
      .stream()
      .map(file -> uploadFile(file))
      .collect(Collectors.toList());
  }
}

