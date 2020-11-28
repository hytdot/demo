package com.example.demo.service;

import com.example.demo.dao.FaultSampleDAO;
import com.example.demo.pojo.FaultSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultSampleService {
    @Autowired
    FaultSampleDAO faultSampleDAO;

    public List<FaultSample> findByHp(int hp) {
        return faultSampleDAO.findByHp(hp);
    }
}
