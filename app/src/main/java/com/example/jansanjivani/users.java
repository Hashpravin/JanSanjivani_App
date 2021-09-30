package com.example.jansanjivani;

public class users {

    public users(){
    }

    public users(String name, String pass, String user,String ph,String add ) {
        this.name = name;
        this.user = user;
        this.pass = pass;
        this.ph = ph ;
        this.add = add ;

    }

    private String name,user,pass,ph,add ;

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
