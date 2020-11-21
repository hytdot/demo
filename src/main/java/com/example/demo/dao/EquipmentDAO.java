package com.example.demo.dao;

import com.example.demo.pojo.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentDAO extends JpaRepository<Equipment,Integer> {
    Equipment findByName(String name);
    List<Equipment> findAllBySupervisor(String supervisor);
    List<Equipment> findAllByState(int state);
}
