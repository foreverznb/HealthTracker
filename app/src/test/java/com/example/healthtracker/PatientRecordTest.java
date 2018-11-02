package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Iterator;

import static org.junit.Assert.*;


public class PatientRecordTest {
    private String title;
    private ArrayList<String> comments;
    private Problem problem;
    private PatientRecord patientRecord;

    @Before
    public void setUp() throws Exception {
        title = "Record 1";
        comments = new ArrayList<String>();
        Date dateStarted = new Date();
        problem = new Problem("Rash",dateStarted,"Red spots are gradually diminishing");
        patientRecord = new  PatientRecord(title,comments,problem);
    }

    @Test
    public void setTitle() throws Exception {
        String newTitle = "Record 2";
        patientRecord.setTitle(newTitle);
        assertEquals(patientRecord.getTitle(),newTitle);
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(patientRecord.getTitle(),title);
    }

    @Test
    public void getComments() throws Exception {
        Integer index = 0;
        assertEquals(patientRecord.getComments(index),comments);
    }

    @Test
    public void addComment() throws Exception {
        String newComment = "new comment";
        patientRecord.addComment(newComment);

    }

    @Test
    public void setComment() throws Exception {
        String newComment = "new comment";
        Integer index = 0;
        patientRecord.setComment(index,newComment);
        assertEquals(patientRecord.getComments(index),"new comment");
    }

    @Test
    public void deleteComment() throws Exception {
        Integer index = 0;
        String prev_comment = comments.get(index);
        patientRecord.deleteComment(index);
        assertFalse(patientRecord.getComments(index) == prev_comment);
    }

    @Test
    public void getProblem() throws Exception {
        assertEquals(problem,patientRecord.getProblem());
    }



}


