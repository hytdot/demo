package com.example.demo.result;

import com.example.demo.pojo.Runstate;

import java.util.List;

public class ResultFault {
    private int code;
    private List<Double> times;
    private List<Double> rates;
    private List<Double> energys1;
    private List<Double> energys2;
    private List<Double> energys3;
    private List<Double> energys4;
    List<Runstate> runstates;

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setTimes(List<Double> times) {
        this.times = times;
    }

    public List<Double> getTimes() {
        return times;
    }

    public void setRates(List<Double> rates) {
        this.rates = rates;
    }

    public List<Double> getRates() {
        return rates;
    }

    public void setEnergys1(List<Double> energys1) {
        this.energys1 = energys1;
    }

    public List<Double> getEnergys1() {
        return energys1;
    }

    public void setEnergys2(List<Double> energys2) {
        this.energys2 = energys2;
    }

    public List<Double> getEnergys2() {
        return energys2;
    }

    public void setEnergys3(List<Double> energys3) {
        this.energys3 = energys3;
    }

    public List<Double> getEnergys3() {
        return energys3;
    }

    public void setEnergys4(List<Double> energys4) {
        this.energys4 = energys4;
    }

    public List<Double> getEnergys4() {
        return energys4;
    }

    public void setRunstates(List<Runstate> runstates) {
        this.runstates = runstates;
    }

    public List<Runstate> getRunstates() {
        return runstates;
    }
}
