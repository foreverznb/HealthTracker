package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by caochenlin on 2018/10/28.
 */
public class BaseRecordTest {
    private String title;
    private ArrayList<String> comments;
    private Problem problem;
    private BaseRecord baseRecord;

    @Before
    public void setUp() throws Exception {
        title = "Record 1";
        comments = new ArrayList<String>();
        Date dateStarted = new Date();
        problem = new Problem("Rash",dateStarted,"Red spots are gradually diminishing");
        baseRecord = new BaseRecord(title,comments,problem);
    }

    @Test
    public void setTitle() throws Exception {
        String newTitle = "Record 2";
        baseRecord.setTitle(newTitle);
        assertEquals(baseRecord.getTitle(),newTitle);
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(baseRecord.getTitle(),title);
    }

    @Test
    public void getComments() throws Exception {
        Integer index = 0;
        assertEquals(baseRecord.getComments(index),comments);
    }

    @Test
    public void addComment() throws Exception {
        String newComment = "new comment";
        baseRecord.addComment(newComment);

    }

    @Test
    public void setComment() throws Exception {
        String newComment = "new comment";
        Integer index = 0;
        baseRecord.setComment(index,newComment);
        assertEquals(baseRecord.getComments(index),"new comment");
    }

    @Test
    public void deleteComment() throws Exception {
        Integer index = 0;
        String prev_comment = comments.get(index);
        baseRecord.deleteComment(index);
        assertFalse(baseRecord.getComments(index) == prev_comment);
    }

    @Test
    public void getProblem() throws Exception {
        assertEquals(problem,baseRecord.getProblem());
    }

}