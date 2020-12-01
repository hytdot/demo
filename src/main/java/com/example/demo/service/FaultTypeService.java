package com.example.demo.service;

import com.example.demo.dao.FaultTypeDAO;
import com.example.demo.pojo.FaultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaultTypeService {
    @Autowired
    FaultTypeDAO faultTypeDAO;

    public FaultType getByType_id(int type_id) {
        return faultTypeDAO.findByType_id(type_id);
    }
}
