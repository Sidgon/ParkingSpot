package database.object;

/**
 * Created by Lavdrim on 22.04.2017.
 */

public class Parkingspot {
    private int idparkingspot;
    private int iduser;
    private String address;
    private String location;
    private double price;
    private double coordinatex;
    private double coordinatey;

    public int getIdparkingspot(){
        return idparkingspot;
    }

    public void setIdparkingspot(int idparkingspot){
        this.idparkingspot = idparkingspot;
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

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
