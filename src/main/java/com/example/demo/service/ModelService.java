package com.example.demo.service;

import com.example.demo.dao.ModelDAO;
import com.example.demo.pojo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    ModelDAO modelDAO;

    public List<Model> modelList(String type) {
        return modelDAO.findByType(type);
    }
}
