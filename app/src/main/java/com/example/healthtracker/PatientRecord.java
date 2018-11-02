package com.example.healthtracker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class PatientRecord extends SimpleRecord {

    //private ArrayList<Photo> photos;
    private String title;
    private String comment;
    private Problem parentProblem;
    private String timestamp;
    private ArrayList<Bitmap> geoLocations;
    private ArrayList<Photo> photos;
    //private int amountOfPhotos;


    public PatientRecord(String title, String comment, Problem parentProblem, String timestamp, ArrayList<Bitmap> geoLocations){
        super(title, comment, parentProblem);

        //this.bodyPart = bodyPart;
        //this.photos = photos;
        //this.bodyPhotos = bodyPhotos;
        this.timestamp = timestamp;
        this.geoLocations = geoLocations;

    }

    public void addGeoLocation(Bitmap geoLocation){
        geoLocations.add(geoLocation);
    }

    public void deleteGeoLocation(Bitmap geoLocation){
        geoLocations.remove(geoLocation);
    }


    public void addPhoto(Photo photo){
        photos.add(photo);
    }

    public void deletePhoto(int index){
        photos.remove(index);
    }

    public Photo getPhoto(int index){
        return photos.get(index);
    }


}
