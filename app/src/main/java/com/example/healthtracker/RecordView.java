package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecordView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_view);
    }

    public void viewRecordsPhotos(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(RecordView.this, SlideShowActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void editRecords(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(RecordView.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void backToProblemView(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(RecordView.this, PatientProblemView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
