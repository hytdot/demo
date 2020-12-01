package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "historical_data")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class HistoricalData {
    @Id
    @Column(name = "data_id")
    int data_id;
    int hp;
    double de_time;
    double fe_time;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
