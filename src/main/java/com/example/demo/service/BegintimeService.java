package com.example.demo.service;

import com.example.demo.dao.BegintimeDAO;
import com.example.demo.pojo.Begintime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BegintimeService {
    @Autowired
    BegintimeDAO begintimeDAO;

    public boolean isExist(String name) {
        Begintime begintime = getByName(name);
        return null != begintime;
    }

    public Begintime getByName(String name) {
        return begintimeDAO.findByName(name);
    }

    public List<Begintime> getByType(String type) {
        return begintimeDAO.findByType(type);
    }

    public void delete(Begintime begintime) {
        begintimeDAO.delete(begintime);
    }

    public void addOrUpdate(Begintime begintime) {
        begintimeDAO.save(begintime);
    }
}
