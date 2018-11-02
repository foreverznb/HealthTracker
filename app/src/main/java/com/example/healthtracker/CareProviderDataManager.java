package com.example.healthtracker;

import java.util.ArrayList;

/**
 * Created by caochenlin on 2018/11/2.
 */

public class CareProviderDataManager {

    private ArrayList<Patient> patients;

    public CareProviderDataManager(ArrayList<Patient> patients){
        this.patients = patients;
    }

    public ArrayList<Patient> elasticLoadPatientList(){
        return patients;
    }

    public ArrayList<Patient> localLoadPatientList(){
        return patients;
    }

    public void savePatientList(){

    }

    public void elasticSearch(String keyword, String searchType){

    }
}
