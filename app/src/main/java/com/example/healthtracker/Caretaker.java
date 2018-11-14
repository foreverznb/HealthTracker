package com.example.healthtracker;

import java.util.ArrayList;
import java.util.List;

public class Caretaker extends User {

    private List<Patient> patientList;

    public Caretaker(String userID, String phone, String email, String userName){
        super(userID, phone, email, userName);
        this.patientList = new ArrayList<Patient>();
    }

    public List<Patient> getPatientList() {
        return this.patientList;
    }

    public void addPatient(Patient newPatient){
        this.patientList.add(newPatient);
    }
}