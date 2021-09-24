package com.example.springbootbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

  public FileDB(String name, String type, byte[] data, String uploadedTime) {
    this.name = name;
    this.type = type;
    this.data = data;
    this.uploadedTime = uploadedTime;
  }

}
