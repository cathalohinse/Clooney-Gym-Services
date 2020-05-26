package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Trainer extends Model{

    private String email;
    private String name;
    private String password;

    //////////////////
    // Constructors //
    //////////////////
    public Trainer(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Trainer(){}

    //////////////
    // methods  //
    //////////////
    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    ///////////////////////
    // Getters & Setters //
    ///////////////////////
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
