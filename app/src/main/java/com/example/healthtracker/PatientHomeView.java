package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PatientHomeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void Search(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, SearchActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void addProblem(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, AddProblemView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void viewMyProblems(View view) {

        // if (problemcount>0){
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(PatientHomeView.this, ViewMyProblems.class);
            // Launch the ViewMyProblems activity
            startActivity(intent);
        //}
        //else{
        //  Toast.makeText(this, "No Problems to View", Toast.LENGTH_LONG).show();

    }

    public void ViewUsers(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, ViewUsers.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Settings(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, UserSettingsActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void viewMap(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, MapView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
    
    public void LogOut(){
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(PatientHomeView.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
