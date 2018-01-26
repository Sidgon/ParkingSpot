package com.example.Lavdrim.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Lavdrim on 11.05.2017.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idparkingspot;
    private Long idprovider;
    private Long idtenant;
    private int days;
    private int isrented;
    private double fullprice;



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getIdparkingspot(){
        return idparkingspot;
    }

    public void setIdparkingspot(Long idparkingspot){
        this.idparkingspot = idparkingspot;
    }

    public Long getIdprovider(){
        return idprovider;
    }

    public void setIdprovider(Long idprovider){
        this.idprovider = idprovider;
    }

    public Long getIdtenant(){
        return idtenant;
    }

    public void setIdtenant(Long idtenant){
        this.idtenant = idtenant;
    }

    public int getDays(){
        return days;
    }

    public void setDays(int days){
        this.days = days;
    }

    public double getFullprice(){
        return fullprice;
    }

    public void setFullprice(double fullprice){
        this.fullprice = fullprice;
    }

    public int getIsrented() {
        return isrented;
    }

    public void setIsrented(int isrented) {
        this.isrented = isrented;
    }
}
