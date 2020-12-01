package com.example.demo.dao;

import com.example.demo.pojo.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDAO extends JpaRepository<Equipment,String> {
    Equipment findByName(String name);
}
