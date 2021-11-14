package com.example.springbootbackend.config.filemessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Implemented to return the information of the file uploaded to the system

@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponse {
  private String fileName;
  private String fileDownloadUri;
  private String fileType;
  private long size;
  private String uploadedTime;
}
