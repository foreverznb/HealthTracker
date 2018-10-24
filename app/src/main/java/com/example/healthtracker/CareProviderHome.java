package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CareProviderHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careprovider_home);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void Search(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, SearchActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void AddPatient(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, AddPatient.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void ViewPatients(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, ViewPatients.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void ViewUsers(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, ViewUsers.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Settings(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, UserSettings.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void careViewMap(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderHome.this, MapView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}