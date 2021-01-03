package com.example.demo.dao;

import com.example.demo.pojo.Begintime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BegintimeDAO extends JpaRepository<Begintime, Integer> {
    Begintime findByName(String name);

    List<Begintime> findByType(String type);

    void deleteByName(String name);
}
