package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by caochenlin on 2018/10/28.
 */

public class SimpleRecord {

    private String title;
    private String comment;
    private Problem parentProblem;

    public SimpleRecord(String title, String comment ,Problem parentProblem){
        this.title = title;
        this.comment = comment;
        this.parentProblem = parentProblem;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public String getTitle(){
        return this.title;
    }

    public String getComment(){ //This is different from Jori's UML
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Problem getProblem(){
        return this.parentProblem;
    }
}
