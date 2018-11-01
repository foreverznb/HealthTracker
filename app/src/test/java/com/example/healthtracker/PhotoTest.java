package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhotoTest {
    private Photo photo;
    private String fileLocation;
    private Record parentRecord;


    @Before
    public void setUp() throws Exception {
        fileLocation = "file location";
        parentRecord = new Record();
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
        assertEquals(photo.getRecord(),parentRecord);
    }


}
