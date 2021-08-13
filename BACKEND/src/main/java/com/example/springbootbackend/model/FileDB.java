package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "file")
public class FileDB {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String name;
  private String type;
  @Lob
  private byte[] data;
  private String uploadedTime;

  public FileDB() {

  }

  public FileDB(String name, String type, byte[] data, String uploadedTime) {
    this.name = name;
    this.type = type;
    this.data = data;
    this.uploadedTime = uploadedTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getUploadedTime() {
    return uploadedTime;
  }

  public void setUploadedTime(String uploadedTime) {
    this.uploadedTime = uploadedTime;
  }
}
