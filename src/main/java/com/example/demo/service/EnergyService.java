package com.example.demo.service;

import com.example.demo.dao.EnergyDAO;
import com.example.demo.pojo.Energy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyService {
    @Autowired
    EnergyDAO energyDAO;

    public List<Energy> energyList(String type, int index) {
        return energyDAO.findByTypeAndIndex(type, index);
    }
}
