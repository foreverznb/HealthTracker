package com.example.healthtracker;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PhotoTest {
    private Photo photo;
    private String fileLocation;
    private PatientRecord parentRecord;


    @Before
    public void setUp() throws Exception {
        fileLocation = "file location";
        Problem p1 = new Problem("Rash", new Date(),"Uncomfortable rash on arm.");
        PatientRecord r1 = new PatientRecord("Day1", "Bruise is small", p1, new Timestamp(new Date().getTime()).toString(), new ArrayList<Bitmap>());
        photo = new Photo(fileLocation,parentRecord);
    }
    @Test
    public void getFile() throws Exception{
        assertEquals(photo.getFile(),fileLocation);

    }

    @Test
    public void setFile() throws Exception{
        String newfileLocation = "file location";
        photo.setFile(newfileLocation);
        assertEquals(photo.getFile(),newfileLocation);
    }

    @Test
    public void getRecord() throws Exception{
        assertEquals(photo.getRecord(), parentRecord);
    }


}
