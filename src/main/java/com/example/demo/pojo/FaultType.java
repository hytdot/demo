package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "fault_type")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class FaultType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    int type_id;
//    rpm,state,fault_bearing,fault_location,fault_diameter
    int rpm;
    String state;
    String fault_bearing;
    String fault_location;
    String fault_diameter;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFault_bearing() {
        return fault_bearing;
    }

    public void setFault_bearing(String fault_bearing) {
        this.fault_bearing = fault_bearing;
    }

    public String getFault_location() {
        return fault_location;
    }

    public void setFault_location(String fault_location) {
        this.fault_location = fault_location;
    }

    public String getFault_diameter() {
        return fault_diameter;
    }

    public void setFault_diameter(String fault_diameter) {
        this.fault_diameter = fault_diameter;
    }
}
