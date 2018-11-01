package com.example.healthtracker;

public class BodyPhoto extends Photo {
    private String name;
    private int pinXLocation;
    private int pinYLocation;

    public BodyPhoto(String fileLocation, Record parentRecord){
        super(fileLocation, parentRecord);

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPinLocation(int xLocation, int yLocation){
        this.pinXLocation = xLocation;
        this.pinYLocation = yLocation;
    }

    public int getPinXLocation(){
        return this.pinXLocation;
    }

    public int getPinYLocation(){
        return this.pinYLocation;
    }

    public  void removePin(){
        this.pinXLocation = 0;
        this.pinYLocation = 0;
    }



}
