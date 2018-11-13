package com.example.healthtracker;

import android.graphics.Bitmap;

import java.util.List;

import io.searchbox.annotations.JestId;

public class User {
    private String userID;
    private String email;
    @JestId
    private String phone;
    private String userName;

    public User(String userID, String phone, String email, String userName){
        this.email = email;
        this.phone = phone;
        this.userID = userID;
        this.userName = userName;
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

    public String getUserName() {
        return this.userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
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