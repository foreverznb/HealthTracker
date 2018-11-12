package com.example.healthtracker;

/**
 * Created by caochenlin on 2018/10/28.
 */

public class Photo {
    private String fileLocation;
    private PatientRecord parentRecord;

    public Photo(String fileLocation,PatientRecord parentRecord){
        this.parentRecord = parentRecord;
        this.fileLocation = fileLocation;

    }

    public String getFile(){
        return this.fileLocation;
    }

    public void setFile(String fileLocation){
        this.fileLocation = fileLocation;
    }

    public PatientRecord getRecord(){
        return this.parentRecord;
    }





}
