package com.example.demo.controller;

import com.example.demo.pojo.Begintime;
import com.example.demo.pojo.Data;
import com.example.demo.pojo.Equipment;
import com.example.demo.result.ResultData;
import com.example.demo.service.BegintimeService;
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
public class DataController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    BegintimeService begintimeService;

//    设备列表和查找设备功能
    @CrossOrigin
    @GetMapping("/api/datas")
    @ResponseBody
    public ResultData datas(@RequestParam("query") String requestQuery) {
        ResultData resultData = new ResultData();
        List<Data> datas = new ArrayList<Data>();
        // 数据列表功能
        if (requestQuery.isEmpty()) {
            List<Equipment> equipmentList = new ArrayList<Equipment>();
            equipmentList.addAll(equipmentService.list());
            List<Begintime> begintimeList = new ArrayList<Begintime>();
            for (int i = 0; i < equipmentList.size(); i++) {
                Begintime begintime = begintimeService.getByName(equipmentList.get(i).getName());
                begintimeList.add(begintime);
            }
            for (int i = 0; i < equipmentList.size(); i++) {
                Data data = new Data();
                data.setName(equipmentList.get(i).getName());
                Begintime begintime = begintimeList.get(i);
                data.setBegintime(begintime.getYear()+"-"+begintime.getMonth()+"-"+begintime.getDay()+" "+begintime.getHour()+":"+begintime.getMinute());
                datas.add(data);
            }
        }
        // 查找数据功能
        else {
            Equipment equipment = equipmentService.getByName(requestQuery);
            Begintime begintime = begintimeService.getByName(requestQuery);
            Data data = new Data();
            data.setName(requestQuery);
            data.setBegintime(begintime.getYear()+"-"+begintime.getMonth()+"-"+begintime.getDay()+" "+begintime.getHour()+":"+begintime.getMinute());
            datas.add(data);
        }

        if (datas.size() != 0) {
            resultData.setCode(200);
        }
        else {
            resultData.setCode(400);
        }
        resultData.setDatas(datas);
        return resultData;
    }
}
