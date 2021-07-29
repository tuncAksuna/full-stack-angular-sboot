package com.example.springbootbackend.controller;

import com.example.springbootbackend.config.filemessage.UploadFileResponse;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.service.FileStorageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping(value = "/api/transactions")
@CrossOrigin
public class FileStorageController {

  private final FileStorageServiceImpl fileStorageService;
  private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);

  @Autowired
  public FileStorageController(FileStorageServiceImpl fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @GetMapping("/files/download/{fileId}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) throws FileNotFoundException {
    FileDB fileDB = fileStorageService.getFileById(fileId);

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(fileDB.getType()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(new ByteArrayResource(fileDB.getData()));
  }

  @PostMapping("/files/uploadSingle")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    FileDB fileDB = fileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/downloadFile/")
      .path(fileDB.getId())
      .toUriString();

    return new UploadFileResponse(fileDB.getName(), fileDownloadUri, file.getContentType(), file.getSize());
  }

  @PostMapping("/files/uploadMultiple")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files)
      .stream()
      .map(file -> uploadFile(file))
      .collect(Collectors.toList());
  }
}

