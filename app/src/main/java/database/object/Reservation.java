package database.object;

/**
 * Created by Lavdrim on 22.04.2017.
 */

public class Reservation {
    private int idreservation;
    private int idparkingspot;
    private int idprovider;
    private int idtenant;
    private int days;
    private int isrented;
    private double fullprice;

    public int getIdreservation(){
        return idreservation;
    }

    public void setIdreservation(int idreservation){
        this.idreservation = idreservation;
    }

    public int getIdparkingspot(){return idparkingspot;}

    public void setIdparkingspot(int idparkingspot){this.idparkingspot = idparkingspot;}

    public int getIdprovider(){
        return idprovider;
    }

    public void setIdprovider(int idprovider){
        this.idprovider = idprovider;
    }

    public int getIdtenant(){
        return idtenant;
    }

    public void setIdtenant(int idtenant){
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