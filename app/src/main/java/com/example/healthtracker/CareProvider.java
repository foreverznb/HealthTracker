package com.example.healthtracker;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The CareProvider class creates CareProvider objects/users and their data
 *
 * @author Michael Boisvert
 * @version 1.0
 * @since 2018-10-20
 */
public class CareProvider extends User implements Serializable {

    private ArrayList<Patient> patientList;


    /**
     * constructor for creating a new CareProvider user and their appropriate profile information designated by parameter values
     *
     * @param phone  the phone number provided by the CareProvider which is associated with their account
     * @param email  the email address provided by the CareProvider which is associated with their account
     * @param userID the userID generated for the CareProvider which is associated with their account
     */
    public CareProvider(String phone, String email, String userID) {
        super(phone, email, userID);
        this.patientList = new ArrayList<Patient>();
    }

    /**
     * singleton method for CareProvider
     */
    public CareProvider() {
    }

    /**
     * gets a list object of the current CareProvider's assigned patients
     *
     * @return returns the CareProviders patient list
     */
    public ArrayList<Patient> getPatientList() {
        return this.patientList;
    }

    /**
     * add's a patient to the current CareProvider's assigned patient list
     *
     * @param newPatient the newpatient to be added to the CareProviders patient list
     */
    public void addPatient(Patient newPatient){
        this.patientList.add(newPatient);
    }

    public void setPatient(Patient updatedPatient, int patientIndex){
        patientList.set(patientIndex, updatedPatient);
    }

    public void setPatientList(ArrayList<Patient> patientList){
        this.patientList = patientList;
    }

    public Patient getPatient(int Index){
        return patientList.get(Index);
    }

    /**
     * TODO
     * Is this used?
     *
     * @param phone
     * @param email
     */
    public void updateUserInfo(String phone, String email){
        super.setEmail(email);
        super.setPhone(phone);
    }

    @Override
    public String toString() {
        if(getUserID() == null) {
            return "";
        }
        return "  ID: " + getUserID() + "\n    phone: " + getPhone() + "\n    email: " + getEmail();
    }
}
