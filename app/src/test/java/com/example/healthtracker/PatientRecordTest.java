package com.example.healthtracker;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.sql.Date;
//import java.util.Date;

import static org.junit.Assert.*;


public class PatientRecordTest {

    private String title;
    private String comment;
    private Problem parentProblem;
    private String timestamp;
    private ArrayList<Bitmap> geoLocations;
    private ArrayList<Photo> photos;
    private Photo photo;
    private PatientRecord patientRecord;

    @Before
    public void setUp() throws Exception {
        title = "PatientRecord";
        comment = "My finger is hurt.";
        photo = new Photo("file location", new Record());
        Date dateStarted = new Date(( new java.util.Date()).getTime());
        timestamp = "16:00:00";
        parentProblem = new Problem("SaveMyFinger",dateStarted,"I felt bad on my finger.");
        geoLocations = new ArrayList<Bitmap>();
        patientRecord = new PatientRecord(title,comment,parentProblem,timestamp, geoLocations);

    }


    @Test
    public void addGeoLocation() throws Exception {

    }

    @Test
    public void addPhoto() throws Exception {
        int index = 0;
        Photo newPhoto = new Photo("file location 1", new Record());
        patientRecord.addPhoto(newPhoto);
        assertEquals(newPhoto, patientRecord.getPhoto(index));
    }

    public void deleteGeoLocation() throws Exception {

    }

    public void getPhoto() throws Exception{
        int index = 0;
        assertEquals(patientRecord.getPhoto(index),photo);
    }


    @Test
    public void deletePhoto() throws Exception {
        Integer index = 0;
        Photo newPhoto = new Photo("file location", new Record());
        patientRecord.addPhoto(newPhoto);
        patientRecord.deletePhoto(index);
        assertFalse(patientRecord.getPhoto(index) == newPhoto);
    }


}


