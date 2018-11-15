package com.example.healthtracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User implements Serializable {

    private List<Problem> problemList;

    public Patient(String userID, String phone, String email, String userName){
        super(userID, phone, email, userName);
        this.problemList = new ArrayList<>();
    }

    public Patient(){};

    public List<Problem> getProblemList() {
        return this.problemList;
    }

    public void setProblem(String title, Problem modifiedProblem){
        //TODO implement
    }

    public void addProblem(Problem newProblem){
        this.problemList.add(newProblem);
        newProblem.setPatient(this);
    }

    public void deleteProblem(Problem problem){
        this.problemList.remove(problem);
    }

}