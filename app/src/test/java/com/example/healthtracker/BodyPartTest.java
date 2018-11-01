package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Iterator;

import static org.junit.Assert.*;


public class BodyPartTest {
    private String name;
    private ArrayList<BodyPhoto> bodyPhotos;
    private BaseRecord parentRecord;
    private BodyPart bodyPart;

    @Before
    public void setUp() throws Exception {
        name = "Hand";
        bodyPhotos = new ArrayList<BodyPhoto>();
        Date dateStarted = new Date();
        parentRecord = new BaseRecord("Title1", new ArrayList<String>(), new Problem("Rash",dateStarted,"Red spots are gradually diminishing"));
        bodyPart = new BodyPart(name, parentRecord);

    }

    @Test
    public void getName() throws Exception {
        assertEquals(name, bodyPart.getName());
    }

    @Test
    public void getBodyPhotos() throws Exception {
        assertEquals(bodyPhotos, bodyPart.getBodyPhotos());
    }

    @Test
    public void getRecord() throws Exception {
        assertEquals(parentRecord, bodyPart.getRecord());
    }

    @Test
    public void setName() throws Exception {
        String newName = "Finger";
        bodyPart.setName(newName);
        assertEquals(name, bodyPart.getName());

    }

    @Test
    public void addBodyPhoto() throws Exception {
        BodyPhoto newBodyPhoto = new BodyPhoto("file location",bodyPart.getRecord());
        bodyPart.addBodyPhoto(newBodyPhoto);
    }

    @Test
    public void removeBodyPhoto() throws Exception {
        Integer index = 0;
        BodyPhoto prev_Bodyphoto = bodyPhotos.get(index);
        bodyPart.removeBodyPhoto(index);
        assertFalse(bodyPart.getBodyPhotos().get(index) == prev_Bodyphoto);
    }

    @Test
    public void setBodyPhoto() throws Exception {

        Integer index = 0;
        BodyPhoto newPhoto = new Photo("file location",bodyPart.getRecord());
        BodyPhoto prev_photo = bodyPhotos.get(index);
        bodyPart.setBodyPhoto(index,newPhoto);

        assertTrue(bodyPart.getBodyPhotos().get(index) == newPhoto && bodyPart.getBodyPhotos().get(index+1) == prev_photo);

    }



}
