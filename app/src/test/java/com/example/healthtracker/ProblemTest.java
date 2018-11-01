package com.example.healthtracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void incrementRecord() throws Exception {
    }

    @Test
    public void getPatientRecords() throws Exception {
    }

    @Test
    public void getCommentRecords() throws Exception {
    }

    @Test
    public void setRecord() throws Exception {

    }

    @Test
    public void addRecord() throws Exception {
    }

    @Test
    public void deleteRecord() throws Exception {
    }

    @Test
    public void getNotifications() throws Exception {
    }

    @Test
    public void setNotification() throws Exception {
    }

    @Test
    public void addNotification() throws Exception {
    }

    @Test
    public void deleteNotification() throws Exception {
    }

    @Test
    public void getPhotos() throws Exception {
    }

    @Test
    public void switchNotification() throws Exception {
    }

}