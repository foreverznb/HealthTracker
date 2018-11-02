package com.example.healthtracker;

import android.app.Notification;

import java.util.ArrayList;
import java.util.Date;

public class Problem {

    private String title;
    private Date dateStarted;
    private String description;
    private Integer numOfRecords;
    private ArrayList<Notification> notifications;
    private Boolean notificationsOn;
    private Patient parentPatient;
    private ArrayList<PatientRecord> patientRecords;
    private ArrayList<SimpleRecord> caregiverRecords;

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
        this.title = newTitle;

    }

    public void setDate(Date newDate){
        this.dateStarted = newDate;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;

    }

    public Integer countRecords(){
        return numOfRecords;
    }

    public ArrayList<PatientRecord> getPatientRecords(){
        return this.patientRecords;
    }


    public ArrayList<SimpleRecord> getCareGiverRecords(){
        return caregiverRecords;
    }

    public void addPatientRecord(PatientRecord patientRecord){
        patientRecords.add(patientRecord);
    }

    public void addCareGiverRecord(SimpleRecord caregiverRecord){
        caregiverRecords.add(caregiverRecord);
    }

    public void deletePatientRecord(Integer index){
        patientRecords.remove(index);
    }

    public void deleteCareGiverRecord(Integer index){
        caregiverRecords.remove(index);
    }

    public ArrayList<Notification> getNotifications(){
        return notifications;

    }

    public void addNotification(Notification newNotification){
        notifications.add(newNotification);
    }

    public void deleteNotification(Integer index){
        notifications.remove(index);
    }


    public void switchNotification(){

    }

    public Boolean notificationStatus(){
        return notificationsOn;
    }

    public Patient getPatient(){
        return parentPatient;
    }
}
