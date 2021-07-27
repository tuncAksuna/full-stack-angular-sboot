package com.example.springbootbackend.controller;

import com.example.springbootbackend.config.filemessage.ResponseFile;
import com.example.springbootbackend.config.filemessage.ResponseMessage;
import com.example.springbootbackend.model.FileDB;
import com.example.springbootbackend.service.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/transactions")
@CrossOrigin
public class FileStorageController {

  private final FileStorageServiceImpl fileStorageService;

  @Autowired
  public FileStorageController(FileStorageServiceImpl fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

    String message = "";
    try {
      fileStorageService.store(file);
      message = "Your file uploaded to system succesfully : " + file.getOriginalFilename();

      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Couldn't upload your file to system : " + file.getOriginalFilename() + " !";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getAllFiles() {
    List<ResponseFile> files = fileStorageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/files/")
        .path(dbFile.getId())
        .toUriString();

      return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = fileStorageService.getFileById(id);

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(fileDB.getData());
  }
}
