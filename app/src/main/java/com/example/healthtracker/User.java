package com.example.healthtracker;

import android.graphics.Bitmap;

import java.util.List;

public abstract class User {
    private String userID;
    private String password;
    private String email;
    private String phone;

    public User(String userID, String password, String phone, String email){
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userID = userID;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Problem> search(String keyword, String type){
        //TODO implement
        return null;
    }

    public Bitmap createMap(){
        //TODO implement
        return null;
    }
}
