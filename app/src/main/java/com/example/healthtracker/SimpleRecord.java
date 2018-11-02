package com.example.healthtracker;

/**
 * Created by caochenlin on 2018/11/1.
 */

public class SimpleRecord {
    private String comment;
    private String title;

    public SimpleRecord(String title,String comment){
        this.title = title;
        this.comment = comment;
    }

    public void setComment(String newComment){
        this.comment = newComment;
    }

    public String getComment(){
        return comment;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
