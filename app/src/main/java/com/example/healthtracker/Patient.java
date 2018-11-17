package com.example.healthtracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User implements Serializable {

    private List<Problem> problemList;

    /**
     * constructor for creating a new Patient user and their appropriate profile information designated by parameter values
     *
     * @param phone  the phone number provided by the Patient which is associated with their account
     * @param email  the email address provided by the Patient which is associated with their account
     * @param userID the userID generated for the Patient which is associated with their account
     */
    public Patient(String phone, String email, String userID){
        super(phone, email, userID);
        this.problemList = new ArrayList<>();
    }

    /**
     * singleton method for Patient
     */
    public Patient() {
    }

    /**
     * gets a list object of the current Patient's documented problems
     *
     * @return returns the Patient's problem list
     */
    public List<Problem> getProblemList() {
        return this.problemList;
    }

    /**
     * TODO
     *
     * @param title
     * @param modifiedProblem
     */
    public void setProblem(String title, Problem modifiedProblem){
        //TODO implement
    }

    /**
     * adds a new problem created by the patient to the patient's list of problems
     *
     * @param newProblem the new problem to be documented for the patient in their problem list
     */
    public void addProblem(Problem newProblem){
        if (this.problemList == null){
            this.problemList = new ArrayList<Problem>();
        }
        this.problemList.add(newProblem);
    }

    /**
     * removes a specified problem from the patient's problem list
     *
     * @param problem the problem specified by the user to be deleted and removed from their problem list
     */
    public void deleteProblem(Problem problem){
        this.problemList.remove(problem);
    }

    /**
     *  TODO
     *  Is this even used?
     * @param phone phone number
     * @param email email address
     */
    public void updateUserInfo(String phone, String email){
        super.setEmail(email);
        super.setPhone(phone);
    }

}