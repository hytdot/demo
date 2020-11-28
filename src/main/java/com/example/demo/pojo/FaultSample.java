package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fault_sample")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class FaultSample {
    int type_id;
    int hp;
    int rpm;
    double de_time;
    double fe_time;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public double getDe_time() {
        return de_time;
    }

    public void setDe_time(double de_time) {
        this.de_time = de_time;
    }

    public double getFe_time() {
        return fe_time;
    }

    public void setFe_time(double fe_time) {
        this.fe_time = fe_time;
    }
}
