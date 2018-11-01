package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Date;

public class PatientRecord extends BaseRecord {

    private BodyPart bodyPart;

    private ArrayList<Photo> photos;

    private ArrayList<Photo> bodyPhotos;

    private Date timestamp;

    private int amountOfPhotos;


    public PatientRecord(String title, ArrayList<String> comments, String address, BodyPart bodyPart, ArrayList<Photo> photos, Date timestamp, Problem parentProblem){
        super(title, comments, parentProblem);

        this.bodyPart = bodyPart;
        this.photos = photos;
        this.bodyPhotos = bodyPhotos;
        this.timestamp = timestamp;

    }

    public String getAddress(){
        String address = "";
        return address;
    }

    public void setAddress(String Address){}

    public BodyPart getBodyPart(){
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart){}

    public ArrayList<Photo> getPhotos(){

        return photos;
    }

    public void addPhoto(Photo photo){}

    public void removePhoto(int index){}

    public void setPhoto(int index, Photo photo){}

    public Date getTimeStamp(){
        return timestamp;
    }

    public void setTimeStamp(){}

    public ArrayList<Photo> getBodyPhotos(){
        return bodyPhotos;
    }

    public int getAmountOfPhotos(){
        return amountOfPhotos;
    }



}
