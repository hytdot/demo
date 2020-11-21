package com.example.demo.result;

import com.example.demo.pojo.Equipment;
import com.example.demo.pojo.User;

import java.util.List;

public class ResultEquipment {
    //响应码
    private int code;
    //提示信息
    private String msg;
    //设备信息
    private List<Equipment> equipment;
    //设备个数
    private int total;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
