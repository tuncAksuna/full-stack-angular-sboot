package com.example.springbootbackend.controller;

import com.example.springbootbackend.config.filemessage.UploadFileResponse;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.service.IFileStorageService;
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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/transactions")
@CrossOrigin(value = "*")
public class FileStorageController {

  private final IFileStorageService IFileStorageService;

  @Autowired
  public FileStorageController(IFileStorageService IFileStorageService) {
    this.IFileStorageService = IFileStorageService;
  }

  @GetMapping("/files/download/{fileId}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) throws FileNotFoundException {

    FileDB fileDB = IFileStorageService.downloadFileById(fileId);

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(fileDB.getType()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(new ByteArrayResource(fileDB.getData()));
  }

  @PostMapping("/files/upload")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    FileDB fileDB = IFileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/downloadFile/")
      .path(fileDB.getId())
      .toUriString();

    return new UploadFileResponse(fileDB.getName(), fileDownloadUri, file.getContentType(), file.getSize(), fileDB.getUploadedTime());
  }

  @PostMapping("/files/uploadMultiple")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.stream(files)
      .map(this::uploadFile)
      .collect(Collectors.toList());
  }

  @GetMapping("/files/downloadAllFiles")
  public ResponseEntity<Map<String, Object>> getAllFiles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
    return IFileStorageService.getAllFiles(page, size);
  }

  @GetMapping("files/downloadOrderByDataASC")
  public Stream<FileDB> getAllFilesOrderBySizeASC() {
    return IFileStorageService.getAllFilesOrderBySizeASC();
  }

  @GetMapping("files/downloadOrderByDataDESC")
  public Stream<FileDB> getAllFilesOrderBySizeDESC() {
    return IFileStorageService.getAllFilesOrderBySizeDESC();
  }

  @GetMapping("files/downloadOrderByUploadedTimeASC")
  public Stream<FileDB> getAllFilesOrderByUploadedTimeASC() {
    return IFileStorageService.getAllFilesOrderByUploadedTimeASC();
  }

  @GetMapping("files/downloadOrderByUploadedTimeDESC")
  public Stream<FileDB> getAllFilesOrderByUploadedTimeDESC() {
    return IFileStorageService.getAllFilesOrderByUploadedTimeDESC();
  }

}

