package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Date;

public class BodyPart {
    private String name;
    private ArrayList<BodyPhoto> bodyPhotos;
    private Record parentRecord;

    public BodyPart(String name, ArrayList<String>comments, String address, BodyPart bodyPart, ArrayList<Photo> photos, Date timestamp){

    }
    public String getName(){

        return name;
    }
    public ArrayList<BodyPhoto> getBodyPhotos(){

        return bodyPhotos;
    }

    public Record getRecord(){

        return parentRecord;
    }

    public void setName(String newName){

    }

    public void addBodyPhoto(BodyPhoto newPhoto){

    }

    public void removeBodyPhoto(int index){

    }

    public void setBodyPhoto(int index, BodyPhoto newPhoto){


    }



}
