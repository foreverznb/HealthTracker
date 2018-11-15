package com.example.healthtracker;

import android.app.Notification;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Problem {

    private String title;
    private Date dateStarted;
    private String description;
    private Integer numOfRecords;
    private ArrayList<Notification> notifications;
    private Boolean notificationsOn = FALSE;
    private Patient parentPatient;
    private ArrayList<PatientRecord> patientRecords;
    private ArrayList<SimpleRecord> caregiverRecords;

    public Problem(){

    }

    public Problem(String title,Date dateStarted,String description){
        this.title = title;
        this.dateStarted = dateStarted;
        this.description = description;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(dateStarted);

        return "title: "+title+"\n"+"start date: "+dateStr+"\n"+"description: "+description;
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
        patientRecords.remove(patientRecords.get(index));
    }

    public void deleteCareGiverRecord(Integer index){
        caregiverRecords.remove(caregiverRecords.get(index));
    }

    public ArrayList<Notification> getNotifications(){
        return this.notifications;
    }

    public void addNotification(Notification newNotification){
        notifications.add(newNotification);
    }

    public void deleteNotification(Integer index){
        notifications.remove(index);
    }


    public void switchNotification(){
        if (notificationsOn  == TRUE){
            notificationsOn = FALSE;
        }
        else{
            notificationsOn = TRUE;
        }
    }

    public Boolean notificationStatus(){
        return notificationsOn;
    }

    public void setPatient(Patient parentPatient){
        this.parentPatient = parentPatient;
    }

    public Patient getPatient(){
        return parentPatient;
    }
}
