package com.example.healthtracker;

import java.util.ArrayList;

/**
 * Created by caochenlin on 2018/11/2.
 */

public class PatientDataManager {
    private ArrayList<Problem> problems = new ArrayList<Problem>();

    public PatientDataManager(ArrayList<Problem> problems){
        this.problems = problems;
    }

    public ArrayList<Problem> elasticLoadProblems(){

        return problems;
    }

    public ArrayList<Problem> localLoadProblems(){

        return problems;
    }

    public void saveProblemList(ArrayList<Problem> problemList){

    }

    public void elasticSearch(String keyword, String searchType){

    }

}
