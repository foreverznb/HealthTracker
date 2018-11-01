package com.example.healthtracker;

/**
 * Created by caochenlin on 2018/10/28.
 */

public class Photo {
    private String fileLocation;
    private Record parentRecord;

    public void Photo(String fileLocation,Record parentRecord){
        this.fileLocation = fileLocation;
        this.parentRecord = parentRecord;
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
