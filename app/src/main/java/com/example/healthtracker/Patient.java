package com.example.healthtracker;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        if (this.problemList == null){
            this.problemList = new ArrayList<Problem>();
        }
        this.problemList.add(newProblem);
    }

    public void deleteProblem(Problem problem){
        this.problemList.remove(problem);
    }

    public void updateUserInfo(String phone, String email, String userName){
        super.setEmail(email);
        super.setPhone(phone);
        super.setUserName(userName);
    }

}