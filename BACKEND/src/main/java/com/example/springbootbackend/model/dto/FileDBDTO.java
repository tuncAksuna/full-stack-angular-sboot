package com.example.springbootbackend.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FileDBDTO {

  private String id;
  private int fileId;
  private String fileName;
  private String fileType;
  private Date uploadedTimeOfFile;


}
