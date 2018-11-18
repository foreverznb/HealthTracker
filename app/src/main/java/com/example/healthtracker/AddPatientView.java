package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddPatientView extends AppCompatActivity {

    private ArrayList<Patient> patients;
    private Context context;
    private EditText inputId;
    private Patient mPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        // Get context
        context = this;

        inputId = (EditText) findViewById(R.id.editText4);

    }

    public void ValidatePatientId(){
        // Read user input
        String patientId = inputId.getText().toString();

        // Read from the elastic search database, obtain a list of registered patient Ids
        ElasticsearchController.getAllPatients getAllMyPatients  = new ElasticsearchController.getAllPatients();
        getAllMyPatients.execute();

        try {
            patients = getAllMyPatients.get();

            // Check whether the patient ID exists
            boolean validID = false;
            for(int i=0;i<patients.size();i++){
                if(patientId == patients.get(i).getUserID()){
                    validID = true;
                    mPatient = patients.get(i);
                }
            }
            if (validID == false){
                Toast.makeText(this,"The patient ID is not valid. Please try again.",Toast.LENGTH_SHORT);
            }
        }catch (ExecutionException e1){
            Log.i("error","execution exception");
        }catch (InterruptedException e2){
            Log.i("error","interrupted exception");
        }

        // If valid.
        // Check whether the patient has already been assigned to another care provider
        if(mPatient.getCareProvider() == null){
            // Add a new Care Provider and save it into the elastic search database

            // Fetch user data
            CareProvider careProvider = UserDataController.loadCareProviderData(this);
            // Add Care Provider
            mPatient.addCareProvider(careProvider);
            // Save Care Provider
            UserDataController.saveCareProviderData(this,careProvider);
        }
        else{
            Toast.makeText(this,"This patient has been assigned to a care provider already.",Toast.LENGTH_LONG);
        }

    }


    public void Add(View view) {
        ValidatePatientId();

        Toast.makeText(this, "Patient Added", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddPatientView.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
