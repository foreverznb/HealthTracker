package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by caochenlin on 2018/11/2.
 */
public class UserDataControllerTest {
    private UserDataController userDataController;

    @Before
    public void setUp() {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        userDataController = new UserDataController(patients);
    }

    @Test
    public void elasticLoadPatientList() {
    }

    @Test
    public void localLoadPatientList() {
    }

    @Test
    public void savePatientList() {
    }

    @Test
    public void elasticSearch() {
    }

}