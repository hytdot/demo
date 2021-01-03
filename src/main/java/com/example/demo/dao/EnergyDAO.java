package com.example.demo.dao;

import com.example.demo.pojo.Energy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyDAO extends JpaRepository<Energy, Integer> {
    List<Energy> findByTypeAndIndex(String type, int index);
}
