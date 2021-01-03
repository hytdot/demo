package com.example.demo.dao;

import com.example.demo.pojo.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelDAO extends JpaRepository<Model, Integer> {
    List<Model> findByType(String type);
}
