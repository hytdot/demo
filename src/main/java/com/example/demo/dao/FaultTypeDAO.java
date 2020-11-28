package com.example.demo.dao;

import com.example.demo.pojo.FaultType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultTypeDAO extends JpaRepository<FaultType, Integer> {
}
