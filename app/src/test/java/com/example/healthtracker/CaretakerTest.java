package com.example.healthtracker;

import android.graphics.Bitmap;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class CaretakerTest {
    @Test
    public void createAccountTest(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Caretaker c = new Caretaker(userID, password, phone, email);
        assertEquals(userID, c.getUserID());
        assertEquals(password, c.getPassword());
        assertEquals(phone, c.getPhone());
        assertEquals(email, c.getEmail());
    }

    @Test
    public void editProfileTest(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Caretaker c = new Caretaker("1", "2", "3", "4");
        c.setEmail(email);
        c.setPassword(password);
        c.setPhone(phone);
        c.setUserID(userID);
        assertEquals(userID, c.getUserID());
        assertEquals(password, c.getPassword());
        assertEquals(phone, c.getPhone());
        assertEquals(email, c.getEmail());
    }

    @Test
    public void addPatient_GetPatients(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Caretaker c = new Caretaker(userID, password, phone, email);
        Patient p = new Patient("1", "2", "3", "4");
        assertEquals(c.getPatientList().size(), 0);
        c.addPatient(p);
        assertEquals(c.getPatientList().size(), 1);
        assertEquals(c.getPatientList().get(0), p);
    }

    @Test
    public void testCaretakerMap(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Caretaker c = new Caretaker(userID, password, phone, email);
        Patient patient1 = new Patient("1", "2", "3", "4");
        String title = "cold";
        Date date = new Date();
        String description = "very sick";
        String title2 = "hot";
        Date date2 = new Date();
        String description2 = "fever";
        Problem p1 = new Problem(title, date, description);
        patient1.addProblem(p1);
        Patient patient2 = new Patient("5", "6", "7", "8");
        Problem p2 = new Problem(title2, date2, description2);
        patient2.addProblem(p2);
        Record r1 = new Record();
        Record r2 = new Record();
        p1.addRecord(r1, true);
        p2.addRecord(r2, true);
        Bitmap data = c.createMap();
        assertNotNull(data);
    }

    @Test
    public void testCaretakerSearch(){
        String userID = "someID";
        String password = "somePass";
        String phone = "780-777-777";
        String email = "someone@ualberta.ca";
        Caretaker c = new Caretaker(userID, password, phone, email);
        Patient patient1 = new Patient("1", "2", "3", "4");
        Patient patient2 = new Patient("5", "6", "7", "8");
        String title = "cold";
        Date date = new Date();
        String description = "very sick";
        String title2 = "hot";
        Date date2 = new Date();
        String description2 = "fever";
        Problem p1 = new Problem(title, date, description);
        Problem p2 = new Problem(title2, date2, description2);
        patient2.addProblem(p2);
        List<Problem> filteredProblems = c.search("someKeyword", "keyword");
        assertNotNull(filteredProblems);
        assertEquals(filteredProblems.size(), 1);
        assertEquals(filteredProblems.get(0), p1);
    }


}
