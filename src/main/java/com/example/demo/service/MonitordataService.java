package com.example.demo.service;

import com.example.demo.dao.MonitordataDAO;
import com.example.demo.pojo.Monitordata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitordataService {
    @Autowired
    MonitordataDAO monitordataDAO;

    public boolean isExist(String name) {
        List<Monitordata> monitordataList = getDatasByName(name);
        return 0 != monitordataList.size();
    }

    public void addOrUpdate(List<Monitordata> monitordatas) {
//        monitordataDAO.saveAndFlush(monitordata);
//        monitordataDAO.save(monitordata);
        monitordataDAO.saveAll(monitordatas);
    }

    public List<Monitordata> getDatasByName(String name) {
        return monitordataDAO.findByName(name);
    }

    public void delete(Monitordata monitordata) {
        monitordataDAO.delete(monitordata);
    }
}
