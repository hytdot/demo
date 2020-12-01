package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Equipment {
    @Id
    @Column(name = "name")
//   (name,model,IR_diameter,OR_diameter,thickness,roller_diameter,pitch_diameter)
    String name;
    String model;
    String IR_diameter;
    String OR_diameter;
    String thickness;
    String roller_diameter;
    String pitch_diameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIR_diameter() {
        return IR_diameter;
    }

    public void setIR_diameter(String IR_diameter) {
        this.IR_diameter = IR_diameter;
    }

    public String getOR_diameter() {
        return OR_diameter;
    }

    public void setOR_diameter(String OR_diameter) {
        this.OR_diameter = OR_diameter;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getRoller_diameter() {
        return roller_diameter;
    }

    public void setRoller_diameter(String roller_diameter) {
        this.roller_diameter = roller_diameter;
    }

    public String getPitch_diameter() {
        return pitch_diameter;
    }

    public void setPitch_diameter(String pitch_diameter) {
        this.pitch_diameter = pitch_diameter;
    }
}
