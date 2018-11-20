package com.example.healthtracker;

import java.io.Serializable;

/**
 * Created by caochenlin on 2018/10/28.
 */


public class CareGiverComment implements Serializable{

    private String messageTitle;
    private String message;

    public CareGiverComment(String title, String comment){
        this.messageTitle = title;
        this.message = comment;
    }

    public CareGiverComment(){
        messageTitle = "";
        message = "";
    }

    public void setTitle(String newTitle){
        this.messageTitle = newTitle;
    }

    public String getTitle(){
        return this.messageTitle;
    }

    public String getComment(){ //This is different from Jori's UML
        return message;
    }

    public void setComment(String comment){
        this.message = comment;
    }
}
