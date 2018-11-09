package com.example.healthtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by caochenlin on 2018/11/2.
 */
public class CareProviderDataManagerTest {
    private CareProviderDataManager careProviderDataManager;

    @Before
    public void setUp() {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        careProviderDataManager = new CareProviderDataManager(patients);
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