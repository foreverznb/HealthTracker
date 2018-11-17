package com.example.healthtracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CareProvider extends User implements Serializable {

    private List<Patient> patientList;

    public CareProvider(String phone, String email, String userID) {
        super(phone, email, userID);
        this.patientList = new ArrayList<Patient>();
    }

    public CareProvider() {
    }

    public List<Patient> getPatientList() {
        return this.patientList;
    }

    public void addPatient(Patient newPatient){
        this.patientList.add(newPatient);
    }

    public void updateUserInfo(String phone, String email){
        super.setEmail(email);
        super.setPhone(phone);
    }
}
