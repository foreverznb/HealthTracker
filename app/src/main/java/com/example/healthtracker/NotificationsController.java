package com.example.healthtracker;

import java.sql.Date;
import java.sql.Time;

public class NotificationsController {
    private Boolean isOn;
    private Problem parentProblem;
    private String repeatType;
    private Time time;
    private Date startDate;


    public void Notification (Date startDate,Time time, String repeatType ){
        this.startDate = startDate;
        this.time = time;
        this.repeatType = repeatType;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Time getTime(){
        return this.time;
    }

    public String getRepeatType(){
        return this.repeatType;
    }

    public void setStartDate(Date newDate) {
        this.startDate = newDate;
    }

    public void setTime(Time newTime){
        this.time = newTime;
    }

    public void setRepeatType(String newRepeatType){
        this.repeatType = newRepeatType;
    }

    public void turnOff(){
        this.isOn = false;
    }

    public void turnOn(){
        this.isOn = true;
    }

    public Problem getProblem(){
        return this.parentProblem;
    }

    public Boolean notificationStatus(){
        return this.isOn;
    }
}
