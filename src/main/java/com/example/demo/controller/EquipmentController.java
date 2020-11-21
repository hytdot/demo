package com.example.demo.controller;

import com.example.demo.dao.EquipmentDAO;
import com.example.demo.pojo.Equipment;
import com.example.demo.result.ResultEquipment;
import com.example.demo.result.ResultUser;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @CrossOrigin
    @GetMapping("/api/equipment")
    @ResponseBody
    public ResultEquipment equipment(@RequestParam("query") String requestQuery,
                                     @RequestParam("pagenum") String requestPagenum,
                                     @RequestParam("pagesize") String requestPagesize) {
        return equipmentService.getEquipmentByName(requestQuery,requestPagenum,requestPagesize);
    }
}
