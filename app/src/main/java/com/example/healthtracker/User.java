package com.example.healthtracker;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

import io.searchbox.annotations.JestId;

public class User implements Serializable {
    private String email;
    private String phone;
    @JestId
    private String userID;

    public User(String phone, String email, String userID) {
        this.email = email;
        this.phone = phone;
        this.userID = userID;
    }

    public User(){

    }

    public String getUserID(){
        return this.userID;
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