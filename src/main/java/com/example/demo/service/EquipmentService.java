package com.example.demo.service;

import com.example.demo.dao.EquipmentDAO;
import com.example.demo.pojo.Equipment;
import com.example.demo.pojo.User;
import com.example.demo.result.ResultEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentDAO equipmentDAO;

    public List<Equipment> list() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return equipmentDAO.findAll(sort);
    }

    public ResultEquipment getEquipmentByName(String requestQuery, String requestPagenum, String requestPagesize) {
        ResultEquipment resultEquipment = new ResultEquipment();
        List<Equipment> equipment = new ArrayList<Equipment>();

        if (requestQuery.isEmpty()) {
            equipment.addAll(list());
        }
        else {
            Equipment equipment1 = equipmentDAO.findByName(requestQuery);
            equipment.add(equipment1);
        }
        if (equipment.size() != 0) {
            resultEquipment.setCode(200);
            resultEquipment.setMsg("获取设备列表成功");
        }
        else {
            resultEquipment.setCode(400);
            resultEquipment.setMsg("获取设备列表失败");
        }
        resultEquipment.setEquipment(equipment);
        resultEquipment.setTotal(equipment.size());
        return resultEquipment;
    }



    public void addOrUpdate(Equipment equipment) {
        equipmentDAO.save(equipment);
    }



    public void deleteById(int id) {
        equipmentDAO.deleteById(id);
    }
}

