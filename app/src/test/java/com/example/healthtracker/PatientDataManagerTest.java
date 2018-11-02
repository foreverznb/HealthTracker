package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by caochenlin on 2018/11/2.
 */
public class PatientDataManagerTest {
    private PatientDataManager patientDataManager;

    @Before
    public void setUp() throws Exception {

        ArrayList<Problem> problems = new ArrayList<Problem>();
        patientDataManager = new PatientDataManager(problems);
    }

    @Test
    public void elasticLoadProblems() throws Exception {
    }

    @Test
    public void localLoadProblems() throws Exception {
    }

    @Test
    public void saveProblemList() throws Exception {
    }

    @Test
    public void elasticSearch() throws Exception {
    }

}