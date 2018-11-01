package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Iterator;

import static org.junit.Assert.*;


public class PatientRecordTest{

    private BodyPart bodyPart;
    private ArrayList<Photo> photos;
    private ArrayList<Photo> bodyPhotos;//ArrayList<BodyPhoto>
    private Date timestamp;
    private int amountOfPhotos;
    private PatientRecord patientRecord;



    @Before
    public void setUp() throws Exception {
        String title = "title 1";
        ArrayList<String>comments = new ArrayList<String>();
        Date dateStarted = new Date();
        Problem problem = new Problem("Rash",dateStarted,"Red spots are gradually diminishing");
        String address = "around";

        ArrayList<BodyPhoto> bp = new ArrayList<BodyPhoto>();
        BaseRecord parentRecord = new BaseRecord(title,comments,problem);

        bodyPart = new BodyPart("Hand",parentRecord);
        photos = new ArrayList<Photo>();
        bodyPhotos = new ArrayList<BodyPhoto>();

        timestamp = new Date();
        amountOfPhotos = 0;

        patientRecord = new PatientRecord(title, comments, address, bodyPart, photos, timestamp, problem);
    }


    @Test
    public void getAddress() throws Exception{
        String address = "";
        assertEquals(address,patientRecord.getAddress());
    }

    @Test
    public void setAddress()throws Exception{
        String newAddress = "new address";
        patientRecord.setAddress(newAddress);
        assertEquals(newAddress, patientRecord.getAddress());
    }


    @Test
    public void getBodyPart() throws Exception {
        assertEquals(bodyPart,patientRecord.getBodyPart());
    }


    @Test
    public void setBodyPart()throws Exception{
        BodyPart bodyPart = new BodyPart("Finger", patientRecord);
        patientRecord.setBodyPart(bodyPart);
        assertEquals(bodyPart, patientRecord.getBodyPart());
    }

    @Test
    public void getPhotos()throws Exception{
        assertEquals(photos,patientRecord.getPhotos());
    }

    @Test
    public void addPhoto()throws Exception{
        Photo newPhoto = new Photo();
        patientRecord.addPhoto(newPhoto);
        //How to test
    }

    @Test
    public void removePhoto()throws Exception{
        Integer index = 0;
        Photo prev_photo = photos.get(index);
        patientRecord.removePhoto(index);
        assertFalse(patientRecord.getPhotos().get(index) == prev_photo);
    }

    @Test
    public void setPhoto()throws Exception{
        Integer index = 0;
        Photo newPhoto = new Photo();
        Photo prev_photo = photos.get(index);
        patientRecord.setPhoto(index,newPhoto);

        assertTrue(patientRecord.getPhotos().get(index) == newPhoto && patientRecord.getPhotos().get(index+1) == prev_photo);
    }

    @Test
    public void getTimeStamp()throws Exception{
        assertEquals(timestamp, patientRecord.getTimeStamp());
    }

    @Test
    public void setTimeStamp()throws Exception{
        Date timestamp = new Date();
        //patientRecord.setTimeStamp();
        assertEquals(timestamp, patientRecord.getTimeStamp());
    }

    @Test
    public void getBodyPhotos()throws Exception{
        assertEquals(bodyPhotos,patientRecord.getBodyPhotos());
    }

    @Test
    public void getAmountOfPhotos()throws Exception{
        assertEquals(amountOfPhotos,patientRecord.getAmountOfPhotos());
    }



}


