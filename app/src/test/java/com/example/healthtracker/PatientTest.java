package com.example.healthtracker;

import android.graphics.Bitmap;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PatientTest {
    @Test
    public void createPatientTest(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient(userID, password, phone, email);
        assertEquals(userID, p.getUserID());
        assertEquals(password, p.getPassword());
        assertEquals(phone, p.getPhone());
        assertEquals(email, p.getEmail());
    }

    @Test
    public void editProfileTest(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient("1", "2", "3", "4");
        p.setEmail(email);
        p.setPassword(password);
        p.setPhone(phone);
        p.setUserID(userID);
        assertEquals(userID, p.getUserID());
        assertEquals(password, p.getPassword());
        assertEquals(phone, p.getPhone());
        assertEquals(email, p.getEmail());
    }

    @Test
    public void add_Remove_GetList_ProblemTest(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient(userID, password, phone, email);
        Problem p1 = new Problem();
        p.addProblem(p1);
        assertEquals(p.getProblemList().get(0), p1);
        assertEquals(p.getProblemList().size(), 1);
        p.deleteProblem(p1);
        assertEquals(p.getProblemList().size(), 0);
    }

    @Test
    public void setProblemTest(){
        // require problem implementation to pass
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient(userID, password, phone, email);
        Problem p1 = new Problem();
        p.addProblem(p1);
        Problem p2 = new Problem();
        //p.setProblem(p1.getTitle(), p2);
        assertEquals(p.getProblemList().get(0), p2);
    }

    @Test
    public void testPatientSearch(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient(userID, password, phone, email);
        Problem p1 = new Problem();
        p.addProblem(p1);
        Problem p2 = new Problem();
        p.addProblem(p2);
        List<Problem> filteredProblems = p.search("someKeyword", "keyword");
        assertNotNull(filteredProblems);
        assertEquals(filteredProblems.size(), 1);
        assertEquals(filteredProblems.get(0), p1);

    }

    @Test
    public void testPatientMap(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Patient p = new Patient(userID, password, phone, email);
        Problem p1 = new Problem();
        p.addProblem(p1);
        Problem p2 = new Problem();
        p.addProblem(p2);
        /*
        need record and problem implementation
        Record r1 = new Record();
        Record r2 = new Record();
        p1.addRecord(r1);
        p2.addRecord(r2);
        */
        Bitmap data = p.createMap();
        assertNotNull(data);
    }
}
