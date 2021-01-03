package com.example.demo.pojo;

import java.util.List;

public class Runstate {
    String name;
    Double time;
    int num;
    String state;
    String result;
    Double rate;
    List<Double> energys;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setEnergys(List<Double> energys) {
        this.energys = energys;
    }

    public List<Double> getEnergys() {
        return energys;
    }
}
