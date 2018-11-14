package com.example.healthtracker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class PatientRecord extends SimpleRecord {

    private String timestamp;
    private ArrayList<Bitmap> geoLocations;
    private ArrayList<Photo> photos;


    public PatientRecord(String title, String comment, Problem parentProblem, String timestamp, ArrayList<Bitmap> geoLocations){
        super(title, comment, parentProblem);
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


}
