package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyPhotoTest {

    private String fileLocation;
    private Record parentRecord;
    private BodyPhoto bodyPhoto;

    @Before
    public void setUp() throws Exception {
        fileLocation = "file location";
        parentRecord = new Record();
        bodyPhoto = new BodyPhoto(fileLocation,parentRecord);
    }

    @Test
    public void setName() throws Exception{
        String new_name = "name";
        bodyPhoto.setName(new_name);
        assertEquals(bodyPhoto.getName(),new_name);
    }

    @Test
    public void getName() throws Exception{
        String new_name = "name";
        bodyPhoto.setName(new_name);
        assertEquals(bodyPhoto.getName(),new_name);
    }

    @Test
    public void setPinLocation()throws Exception{
        int new_pinXLocation = 10;
        int new_pinYLocation = 11;
        bodyPhoto.setPinLocation(new_pinXLocation,new_pinYLocation);
        assertEquals(bodyPhoto.getPinXLocation(),new_pinXLocation);
        assertEquals(bodyPhoto.getPinYLocation(),new_pinYLocation);
    }

    @Test
    public void getPinXLocation() throws Exception{
        int new_pinXLocation = 10;
        int new_pinYLocation = 11;
        bodyPhoto.setPinLocation(new_pinXLocation,new_pinYLocation);
        assertEquals(bodyPhoto.getPinXLocation(),new_pinXLocation);

    }

    @Test
    public void getPinYLocation()throws Exception{
        int new_pinXLocation = 10;
        int new_pinYLocation = 11;
        bodyPhoto.setPinLocation(new_pinXLocation,new_pinYLocation);
        assertEquals(bodyPhoto.getPinYLocation(),new_pinYLocation);
    }

    @Test
    public  void removePin()throws Exception{
        int new_pinXLocation = 10;
        int new_pinYLocation = 11;
        bodyPhoto.setPinLocation(new_pinXLocation,new_pinYLocation);
        bodyPhoto.removePin();
        assertEquals(bodyPhoto.getPinXLocation(),0);
        assertEquals(bodyPhoto.getPinYLocation(),0);
    }
    

}
