package com.example.demo.dao;

import com.example.demo.pojo.Monitordata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitordataDAO extends JpaRepository<Monitordata, Integer> {
    List<Monitordata> findByName(String name);

    void deleteByName(String name);
}
