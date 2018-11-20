package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewPatientsProblems extends AppCompatActivity {

    private ArrayList<Problem> pProblems;
    private ListView pListView;
    private TextView idText;
    private ArrayAdapter<Problem> pArrayAdapter;
    private Patient myPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients_problems);

        // get patient#
        Intent intent = getIntent();
        CareProvider careProvider = UserDataController.loadCareProviderData(this);
        int patientNum = intent.getIntExtra("patientNum", 0);
        myPatient = careProvider.getPatientList().get(patientNum);

        //Load patient data
        pProblems = myPatient.getProblemList();
        idText = findViewById(R.id.selected_patient_ID);
        String patientID = myPatient.getUserID();
        idText.setText("Patient: " + patientID);

        // Create an instance of an array adapter
        pArrayAdapter = new ArrayAdapter<Problem>(this, android.R.layout.simple_list_item_1, pProblems);

        // Set an adapter for the list view
        pListView = findViewById(R.id.pProblem_list_view);
        pListView.setAdapter(pArrayAdapter);

        // Select a PatientProblem to view
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewPatientsProblems.this,CareProviderProblemView.class);

                Bundle bd = new Bundle();
                bd.putInt("patientNum", patientNum);
                bd.putInt("problemNum", position);
                intent.putExtras(bd);


                startActivity(intent);
            }
        });


    }




}
