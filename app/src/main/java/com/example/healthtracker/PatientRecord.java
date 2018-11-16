package com.example.healthtracker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class PatientRecord{

    private String RecordTitle;
    private String comment;
    private String timestamp;
    private ArrayList<Bitmap> geoLocations;
    private ArrayList<Photo> photos;


    public PatientRecord(String title, String comment, String timestamp, ArrayList<Bitmap> geoLocations){
        this.RecordTitle = title;
        this.comment = comment;
        this.timestamp = timestamp;
        this.geoLocations = geoLocations;
        this.getTitle();
    }

    public void addGeoLocation(Bitmap geoLocation){
        geoLocations.add(geoLocation);
    }

    public void deleteGeoLocation(Bitmap geoLocation){
        geoLocations.remove(geoLocation);
    }


    public void addPhoto(Photo photo){
        photos.add(photo);
        photo.setParentRecord(this);
    }

    public void deletePhoto(int index){
        photos.remove(index);
    }

    public Photo getPhoto(int index){
        return photos.get(index);
    }

    public void setTitle(String newTitle){
        this.RecordTitle = newTitle;
    }

    public String getTitle(){
        return this.RecordTitle;
    }

    public String getComment(){ //This is different from Jori's UML
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }


}
