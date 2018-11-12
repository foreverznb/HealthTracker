package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddPatientView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
    }

    public void Return2(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddPatientView.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Add(View view) {
        Toast.makeText(this, "Patient Added", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddPatientView.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
