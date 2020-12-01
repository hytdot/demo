package com.example.demo.controller;

import com.example.demo.result.ResultEquipment;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        ResultEquipment resultEquipment = new ResultEquipment();
        return resultEquipment;
    }
}
