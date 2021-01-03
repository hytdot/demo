package com.example.demo.controller;

import com.example.demo.pojo.Begintime;
import com.example.demo.pojo.Equipment;
import com.example.demo.pojo.Monitordata;
import com.example.demo.result.ResultEquipment;
import com.example.demo.service.BegintimeService;
import com.example.demo.service.EquipmentService;
import com.example.demo.service.MonitordataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    BegintimeService begintimeService;
    @Autowired
    MonitordataService monitordataService;

//    设备列表和查找设备功能
    @CrossOrigin
    @GetMapping("/api/equipment")
    @ResponseBody
    public ResultEquipment equipments(@RequestParam("query") String requestQuery,
                                     @RequestParam("pagenum") String requestPagenum,
                                     @RequestParam("pagesize") String requestPagesize) {
        ResultEquipment resultEquipment = new ResultEquipment();
        List<Equipment> equipments = new ArrayList<Equipment>();
        if (requestQuery.isEmpty()) {
            equipments.addAll(equipmentService.list());
        }
        else {
            System.out.println("query:"+requestQuery);
            Equipment equipment = equipmentService.getByName(requestQuery);
            System.out.println(equipment);
            equipments.add(equipment);
        }
        if (equipments.size() != 0) {
            resultEquipment.setCode(200);
            resultEquipment.setMsg("获取设备列表成功");
        }
        else {
            resultEquipment.setCode(400);
            resultEquipment.setMsg("获取设备列表失败");
        }
        resultEquipment.setEquipments(equipments);
        resultEquipment.setTotal(equipments.size());
        return resultEquipment;
    }

//    修改设备功能
    @CrossOrigin
    @PostMapping(value = "/api/editequipment")
    @ResponseBody
    public ResultEquipment editEquipment(@RequestBody Equipment requestEquipment) {
        ResultEquipment resultEquipment = new ResultEquipment();
        // 设备存在，返回code200
        if (equipmentService.isExist(requestEquipment.getName())) {
            equipmentService.addOrUpdate(requestEquipment);
            resultEquipment.setCode(200);
            return resultEquipment;
        }
        // 设备不存在，修改设备失败
        else {
            resultEquipment.setCode(201);
            return resultEquipment;
        }
    }

//    删除设备功能
    @CrossOrigin
    @GetMapping("/api/deleequipment")
    @ResponseBody
    public ResultEquipment deleEquipment(@RequestParam("name") String name) {
        ResultEquipment resultEquipment = new ResultEquipment();
        // 设备存在，返回code200
        if (equipmentService.isExist(name)) {
            equipmentService.deleteByName(name);
            Begintime begintime = begintimeService.getByName(name);
            begintimeService.delete(begintime);
            List<Monitordata> monitordataList = monitordataService.getDatasByName(name);
            for (int i = 0; i < monitordataList.size(); i++) {
                monitordataService.delete(monitordataList.get(i));
            }
            resultEquipment.setCode(200);
            return resultEquipment;
        }
        // 设备不存在，删除设备失败
        else {
            resultEquipment.setCode(201);
            return resultEquipment;
        }
    }

//    增加设备功能
    @CrossOrigin
    @PostMapping("/api/addequipment")
    @ResponseBody
    public ResultEquipment addEquipment(@RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("type") String type,
                                        @RequestParam("ir_diameter") String ir_diameter,
                                        @RequestParam("or_diameter") String or_diameter,
                                        @RequestParam("roller_diameter") String roller_diameter) {
//        System.out.println("用户名："+requestUser.getUsername());
//        System.out.println("密码："+requestUser.getPassword());
        ResultEquipment resultEquipment = new ResultEquipment();

        // 加设备表
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setType(type);
        equipment.setIR_diameter(ir_diameter);
        equipment.setOR_diameter(or_diameter);
        equipment.setRoller_diameter(roller_diameter);
        if (equipmentService.isExist(name)) {
            resultEquipment.setCode(202);
            return resultEquipment;
        }
        else {
            equipmentService.addOrUpdate(equipment);
        }

        // 增加时间表
        Date now_date = new Date();
        Begintime begintime = new Begintime();
        begintime.setName(name);
        begintime.setType(type);
        begintime.setYear(now_date.getYear() + 1900);
        begintime.setMonth(now_date.getMonth() + 1);
        begintime.setDay(now_date.getDate());
        begintime.setHour(now_date.getHours());
        begintime.setMinute(now_date.getMinutes());
        begintime.setSecond(now_date.getSeconds());
        if (begintimeService.isExist(name)) {
            resultEquipment.setCode(202);
            return resultEquipment;
        }
        else {
            begintimeService.addOrUpdate(begintime);
        }

        // 增加监测数据表
        String path = "F:\\testData\\";
        File newFile = new File(path + file.getOriginalFilename());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> arrayList = new ArrayList<Double>();
        if (monitordataService.isExist(name)) {
            resultEquipment.setCode(202);
            return resultEquipment;
        }
        try {
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(newFile),"UTF-8");
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                str = str.replaceAll("\r|\n", "");
                arrayList.add(Double.valueOf(str));
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Monitordata> monitordataList = new ArrayList<Monitordata>();
        for (int i = 0; i < arrayList.size(); i++) {
            Monitordata monitordata = new Monitordata();
            monitordata.setName(name);
            monitordata.setNum(i);
            monitordata.setData(arrayList.get(i));
            monitordataList.add(monitordata);
        }
        monitordataService.addOrUpdate(monitordataList);
        System.out.println("增加设备");
        System.out.println(newFile);
        System.out.println(name);
        System.out.println(type);
        resultEquipment.setCode(200);
        return resultEquipment;
    }
}
