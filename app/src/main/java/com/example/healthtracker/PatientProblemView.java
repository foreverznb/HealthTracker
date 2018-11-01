package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PatientProblemView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_problem_view);
    }

    public void viewProblemsPhotos(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientProblemView.this, SlideShowActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void editProblem(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientProblemView.this, EditProblem.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void returnToMyProblemView(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientProblemView.this, ViewMyProblems.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void addPatientRecord(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientProblemView.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}