package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by caochenlin on 2018/10/28.
 */

public class BaseRecord {

    private String title;
    private ArrayList<String> comments;
    private Problem parentProblem;

    public BaseRecord(String title,ArrayList<String> comments,Problem parentProblem){
        this.title = title;
        this.comments = comments;
        this.parentProblem = parentProblem;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public String getTitle(){
        return this.title;
    }

    public String getComments(Integer index){ //This is different from Jori's UML
        return comments.get(index);
    }

    public void addComment(String comment){
        comments.add(comment);
    }

    public void setComment(Integer index,String comment){
        comments.set(index,comment);
    }

    public void deleteComment(Integer index){
        comments.remove(index);
    }

    public Problem getProblem(){
        return this.parentProblem;
    }
}
