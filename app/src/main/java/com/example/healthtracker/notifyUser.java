package com.example.healthtracker;

import java.util.Date;

public class notifyUser {
    private Boolean isOn;
    private String repeatType;
    private Date time;
    private Date startDate;

    public notifyUser(){

    }

    public notifyUser(String repeatType, Date time, Date startDate){
       this.repeatType = repeatType;
       this.time = time;
       this.startDate = startDate;
    }

}
