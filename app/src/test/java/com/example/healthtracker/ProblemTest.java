package com.example.healthtracker;

import android.app.Notification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.*;

/**
 * Created by caochenlin on 2018/10/28.
 */
public class ProblemTest {
    private Problem problem;
    private String title;
    private Date dateStarted;
    private String description;

    @Before
    public void setUp() throws Exception {
        // Create an instance of the Problem class
        title = "Rash";
        dateStarted = new Date();
        description = "A lot of red spots on my skin.";
        problem = new Problem(title,dateStarted,description);
    }


    @Test
    public void getTitle() throws Exception {
        assertEquals(problem.getTitle(),"Rash");
    }

    @Test
    public void getDate() throws Exception {
        assertEquals(problem.getDate(),dateStarted);
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(problem.getDescription(),description);
    }

    @Test
    public void setTitle() throws Exception {
        problem.setTitle("Big Rash");
        assertEquals(problem.getTitle(),"Big Rash");
    }

    @Test
    public void setDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = "2018/08/07";
        Date date = sdf.parse(dateString);
        problem.setDate(date);
        assertEquals(problem.getDate(),date);
    }

    @Test
    public void setDescription() throws Exception {
        problem.setDescription("Red spots are gradually diminishing");
        assertEquals(problem.getDescription(),"Red spots are gradually diminishing");
    }

    @Test
    public void countRecord() throws Exception {

    }

    @Test
    public void getPatientRecords() throws Exception {
    }

    @Test
    public void getCareGiverRecords() throws Exception {
    }


    @Test
    public void addPatientRecord() throws Exception {
    }

    @Test
    public void addCareGiverRecord() throws Exception {
    }


    @Test
    public void deleteRecord() throws Exception {
    }

    @Test
    public void getNotifications() throws Exception {
        Notification myNotification = new Notification();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        notifications.add(myNotification);
        problem.addNotification(myNotification);
        assertEquals(notifications,problem.getNotifications());
    }

    @Test
    public void addNotification() throws Exception {
        Notification myNotification = new Notification();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        notifications.add(myNotification);
        problem.addNotification(myNotification);
        assertEquals(notifications,problem.getNotifications());
    }

    @Test
    public void deleteNotification() throws Exception {

    }

    @Test
    public void switchNotification() throws Exception {
        Boolean myNotification = problem.notificationStatus();
        switchNotification();
        Boolean myNotification2 = problem.notificationStatus();
        assertFalse(myNotification == myNotification2);
    }

    @Test
    public void notificationStatus() throws Exception {
        
    }


    @Test
    public void getPatient() throws Exception{
        Patient patient = new Patient("P001","P001","123-456-789","patient1@health.com");
        problem.setPatient(patient);
        assertEquals(problem.getPatient(),patient);
    }

    @Test
    public void setPatient() throws Exception{
        Patient newPatient = new Patient("P002","P002","111-222-333","patient2@health.com");
        problem.setPatient(newPatient);
        assertEquals(problem.getPatient(),newPatient);
    }

}