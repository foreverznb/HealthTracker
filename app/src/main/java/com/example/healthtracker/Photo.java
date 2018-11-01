package com.example.healthtracker;

/**
 * Created by caochenlin on 2018/10/28.
 */

public class Photo {
    private String fileLocation;
    private Record parentRecord;

    public Photo(String fileLocation,Record parentRecord){
        this.parentRecord = parentRecord;
        this.fileLocation = fileLocation;

    }

    public String getFile(){
        return this.fileLocation;
    }

    public void setFile(String fileLocation){
        this.fileLocation = fileLocation;
    }

    public Record getRecord(){
        return this.parentRecord;
    }





}
