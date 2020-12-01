package com.example.demo.dao;

import com.example.demo.pojo.RealtimeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtimeDataDAO extends JpaRepository<RealtimeData, Integer> {
}
