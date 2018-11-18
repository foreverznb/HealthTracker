package com.example.healthtracker;

import android.app.Notification;
import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Problem implements Serializable {

    private String ProblemTitle;
    private Date dateStarted;
    private String description;
    private Integer numOfRecords;
    // if we use the android built in notifications then they cant be here or elastic search will break
    private List<notifyUser> notifications;
    private Boolean notificationsOn = FALSE;
    private ArrayList<PatientRecord> patientRecords;
    private ArrayList<CareGiverComment> caregiverRecords;

    public Problem(){

    }

    public Problem(String title,Date dateStarted,String description){
        this.ProblemTitle = title;
        this.dateStarted = dateStarted;
        this.description = description;
        this.numOfRecords = 0;          // Refer to the number of records created by the patient
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(dateStarted);
        Log.d("number of records: ",String.valueOf(numOfRecords));
        return "title: "+ProblemTitle+"\n"+"start date: "+dateStr+"\n"+"description: "+description+"\n"+"number of records: "+String.valueOf(numOfRecords);
    }

    public String getTitle(){

        return ProblemTitle;
    }

    public Date getDate(){

        return dateStarted;
    }

    public String getDescription(){

        return description;
    }

    private void setTitle(String newTitle){
        this.ProblemTitle = newTitle;

    }

    private void setDate(Date newDate){
        this.dateStarted = newDate;
    }

    private void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void update(String title, Date date, String description){
        setDate(date);
        setTitle(title);
        setDescription(description);
    }

    public Integer countRecords(){
        return numOfRecords;
    }

   /* public ArrayList<PatientRecord> getPatientRecords(){
        return this.patientRecords;
    }


    public ArrayList<CareGiverComment> getCareGiverRecords(){
        return caregiverRecords;
    }

    public void addPatientRecord(PatientRecord patientRecord){
        patientRecords.add(patientRecord);
        numOfRecords += 1;
    }

    public void addCareGiverRecord(CareGiverComment caregiverRecord){
        caregiverRecords.add(caregiverRecord);
    }

    public void deletePatientRecord(Integer index){
        patientRecords.remove(patientRecords.get(index));
        numOfRecords -= 1;
    }

    public void deleteCareGiverRecord(Integer index){
        caregiverRecords.remove(caregiverRecords.get(index));
    }

    public ArrayList<notifyUser> getNotifications(){
        return this.notifications;
    }

    public void addNotification(notifyUser newNotification){
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
    }*/

}
