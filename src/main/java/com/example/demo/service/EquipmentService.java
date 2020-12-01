package com.example.demo.service;

import com.example.demo.dao.EquipmentDAO;
import com.example.demo.pojo.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentDAO equipmentDAO;

    public List<Equipment> list() {
        return equipmentDAO.findAll();
    }

    public Equipment getByName(String name) {
        return equipmentDAO.findByName(name);
    }

    public void addOrUpdate(Equipment equipment) {
        equipmentDAO.save(equipment);
    }

}
