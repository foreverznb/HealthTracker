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
    public void setUp() throws Exception {
        java.util.Date ud = new java.util.Date();
        startDate = new Date(ud.getTime());
        java.util.Date ud_time = new java.util.Date();
        time = new Time(ud_time.getTime());
        repeatType = "week";
        isOn = true;
        parentProblem = new Problem("testproblem",new java.util.Date(), "test");

    }

    @Test
    public void getStartDate() throws Exception{

    }

    @Test
    public void getTime()throws Exception{

    }

    @Test
    public void getRepeatType()throws Exception{

    }

    @Test
    public void setStartDate(Date newDate) throws Exception{

    }

    @Test
    public void setTime(Time newTime)throws Exception{

    }

    @Test
    public void setRepeatType(String newRepeatType)throws Exception{

    }

    @Test
    public void turnOff()throws Exception{

    }

    @Test
    public void turnOn()throws Exception{

    }

    @Test
    public void getProblem()throws Exception{

    }

    @Test
    public void notificationStatus()throws Exception{

    }


}
