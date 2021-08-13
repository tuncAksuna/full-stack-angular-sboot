package com.example.springbootbackend.config.filemessage;

public class UploadFileResponse {
  private String fileName;
  private String fileDownloadUri;
  private String fileType;
  private long size;
  private String uploadedTime;


  public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String uploadedTime) {
    this.fileName = fileName;
    this.fileDownloadUri = fileDownloadUri;
    this.fileType = fileType;
    this.size = size;
    this.uploadedTime = uploadedTime;
  }

  public String getFileName() {
    return fileName;
  }

  public String getUploadedTime() {
    return uploadedTime;
  }

  public void setUploadedTime(String uploadedTime) {
    this.uploadedTime = uploadedTime;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileDownloadUri() {
    return fileDownloadUri;
  }

  public void setFileDownloadUri(String fileDownloadUri) {
    this.fileDownloadUri = fileDownloadUri;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }
}
