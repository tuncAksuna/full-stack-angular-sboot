package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDB, String> {

}
