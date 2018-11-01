package com.example.healthtracker;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {

    private List<Problem> problemList;

    public Patient(String userID, String password, String phone, String email){
        super(userID, password, phone, email);
        this.problemList = new ArrayList<>();
    }

    public List<Problem> getProblemList() {
        return this.problemList;
    }

    public void setProblem(String title, Problem modifiedProblem){
        //TODO implement
    }

    public void addProblem(Problem newProblem){
        this.problemList.add(newProblem);
    }

    public void deleteProblem(Problem problem){
        this.problemList.remove(problem);
    }

}
