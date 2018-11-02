package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Date;

public class PatientRecord extends SimpleRecord {

    //private ArrayList<Photo> photos;
    private String title;
    private ArrayList<String> comments;
    private Problem parentProblem;

    //private Date timestamp;
    //private int amountOfPhotos;


    public PatientRecord(String title, ArrayList<String> comments, Problem parentProblem){
        this.comments = super(comments);

        //this.bodyPart = bodyPart;
        //this.photos = photos;
        //this.bodyPhotos = bodyPhotos;
        //this.timestamp = timestamp;
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

    /*
    public ArrayList<Photo> getPhotos(){

        return photos;
    }

    public void addPhoto(Photo photo){}

    public void removePhoto(int index){}

    public void setPhoto(int index, Photo photo){}

*/

}
