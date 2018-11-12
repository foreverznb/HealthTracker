package com.example.healthtracker;

import android.app.Notification;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

public class NotificationControllerTest {
    private Boolean isOn;
    private Problem parentProblem;
    private String repeatType;
    private Time time;
    private Date startDate;

    @Before
    public void setUp() {
        java.util.Date ud = new java.util.Date();
        startDate = new Date(ud.getTime());
        java.util.Date ud_time = new java.util.Date();
        time = new Time(ud_time.getTime());
        repeatType = "week";
        isOn = true;
        parentProblem = new Problem("testproblem",new java.util.Date(), "test");

    }

    @Test
    public void getStartDate() {

    }

    @Test
    public void getTime() {

    }

    @Test
    public void getRepeatType() {

    }

    @Test
    public void setStartDate(Date newDate) {

    }

    @Test
    public void setTime(Time newTime) {

    }

    @Test
    public void setRepeatType(String newRepeatType) {

    }

    @Test
    public void turnOff() {

    }

    @Test
    public void turnOn() {

    }

    @Test
    public void getProblem() {

    }

    @Test
    public void notificationStatus() {

    }


}
