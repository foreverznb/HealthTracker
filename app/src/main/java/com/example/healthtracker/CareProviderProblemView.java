package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CareProviderProblemView extends AppCompatActivity {

    private ArrayList<Problem> pProblems;
    private ListView pListView;
    private TextView titleText;
    private TextView dateText;
    private TextView desText;
    private TextView recordText;
    private ArrayAdapter<PatientRecord> rArrayAdapter;
    private Patient myPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_problem_view);

        Intent intent = getIntent();
        CareProvider careProvider = UserDataController.loadCareProviderData(this);
        int patientNum = intent.getIntExtra("patientNum", 0);
        myPatient = careProvider.getPatientList().get(patientNum);


    }

    public void returnToProblemsList(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, ViewPatientsProblems.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void addCareProviderComment(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, AddCareProviderCommentView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void viewProblemsPhotos(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, SlideShowActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

}
