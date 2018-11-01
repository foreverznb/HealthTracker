package com.example.healthtracker;

import android.app.Notification;

import java.util.ArrayList;
import java.util.Date;

public class Problem {

    private String title;
    private Date dateStarted;
    private String description;
    private Integer numOfRecords;
    private ArrayList<Record> patientRecords;
    private ArrayList<Record> commentRecords;
    private ArrayList<Photo> photos;
    private ArrayList<Notification> notifications;
    private Boolean notificationsOn;
    private Patient parentPatient;

    public Problem(String title,Date dateStarted,String description){
        this.title = title;
        this.dateStarted = dateStarted;
        this.description = description;
    }

    public String getTitle(){

        return title;
    }

    public Date getDate(){

        return dateStarted;
    }

    public String getDescription(){

        return description;
    }

    public void setTitle(String newTitle){

    }

    public void setDate(Date newDate){

    }

    public void setDescription(String newDescription){

    }

    public void incrementRecord(Integer number){

    }

    public void getPatientRecords(){

    }

    public void getCommentRecords(){

    }

    public void setRecord(String title,Record modifiedRecord,Boolean isPatient){

    }

    public void addRecord(Record newRecord,Boolean isPatient){

    }

    public void deleteRecord(String title,Boolean isPatient){

    }

    public void getNotifications(){

    }

    public void setNotification(Integer index, Notification modifiedNotification){

    }

    public void addNotification(Notification newNotification){

    }

    public void deleteNotification(Integer index){

    }

    public void getPhotos(){

    }

    public void switchNotification(){

    }

    public void notificationStatus(){

    }

    public void getPatient(){

    }
}
