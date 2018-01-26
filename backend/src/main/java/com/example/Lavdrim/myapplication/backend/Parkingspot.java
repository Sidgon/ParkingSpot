package com.example.Lavdrim.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * 11.05.2017.
 */

@Entity
public class Parkingspot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long iduser;
    private String address;
    private String location;
    private double price;
    private double coordinatex;
    private double coordinatey;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public double getCoordinatex() {
        return coordinatex;
    }

    public void setCoordinatex(double coordinatex) {
        this.coordinatex = coordinatex;
    }

    public double getCoordinatey() {
        return coordinatey;
    }

    public void setCoordinatey(double coordinatey) {
        this.coordinatey = coordinatey;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }
}