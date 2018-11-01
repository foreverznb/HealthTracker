package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Date;

public class BodyPart {
    private String name;
    private ArrayList<BodyPhoto> bodyPhotos;
    private BaseRecord parentRecord;

    public BodyPart(String name,BaseRecord parentRecord){
        this.name = name;
        this.parentRecord = parentRecord;
        //this.bodyPhotos = bodyPhotos;
    }
    public String getName(){

        return name;
    }
    public ArrayList<BodyPhoto> getBodyPhotos(){

        return bodyPhotos;
    }

    public BaseRecord getRecord(){

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
