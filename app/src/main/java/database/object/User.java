package database.object;

/**
 * Created by Lavdrim on 22.04.2017.
 */

public class User {
    private int iduser;
    private String login;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phone;
    private String password;

    public int getIduser(){
        return iduser;
    }

    public void setIduser(int iduser){
        this.iduser = iduser;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
