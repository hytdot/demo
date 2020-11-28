package com.example.demo.dao;

import com.example.demo.pojo.FaultSample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaultSampleDAO extends JpaRepository<FaultSample, Integer> {
    List<FaultSample> findByHp(int hp);
}
