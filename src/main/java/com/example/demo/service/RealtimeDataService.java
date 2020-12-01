package com.example.demo.service;

import com.example.demo.dao.RealtimeDataDAO;
import com.example.demo.pojo.RealtimeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealtimeDataService {
    @Autowired
    RealtimeDataDAO realtimeDataDAO;

    public List<RealtimeData> list() {
        return realtimeDataDAO.findAll();
    }
}
