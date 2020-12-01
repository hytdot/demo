package com.example.demo.service;

import com.example.demo.dao.HistoricalDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricalDataService {
    @Autowired
    HistoricalDataDAO historicalDataDAO;
}
