package com.example.healthtracker;

import java.sql.Date;
import java.sql.Time;

public class Notification {
    private Boolean isOn;
    private Problem parentProblem;
    private String repeatType;
    private Time time;
    private Date startDate;

    public Notification(String repeatType, Time time, Date startDate){
        this.repeatType = repeatType;
        this.time = time;
        this.startDate = startDate;
    }

}
