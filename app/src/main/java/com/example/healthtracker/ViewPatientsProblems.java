package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewPatientsProblems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients_problems);
    }

    /*
        // add listener to detect button click on items in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // method to initiate after listener detects click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an intent object containing the bridge to between the two activities
                Intent intent = new Intent(ViewPatientsProblems.this, .class);
                // Launch the browse emotions activity
                startActivity(intent);
                }
            }*/

    public void returnToPatientsList(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(ViewPatientsProblems.this, ViewPatients.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
