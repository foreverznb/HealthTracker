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

    public Boolean ValidatePatientId(){
        // Read user input
        String patientId = inputId.getText().toString();
        boolean validID = false;
        // Read from the elastic search database, obtain a list of registered patient Ids
        ElasticsearchController.getAllPatients getAllMyPatients  = new ElasticsearchController.getAllPatients();
        getAllMyPatients.execute();

        try {
            patients = getAllMyPatients.get();
            System.out.println(patients);

            // Check whether the patient ID exists

            for(int i=0;i<patients.size();i++){

                if(patientId.equals(patients.get(i).getUserID())){
                    validID = true;
                    mPatient = patients.get(i);
                }
            }
            System.out.println("patientId is "+validID);
            if (!validID){
                Toast.makeText(this,"The patient ID is not valid. Please try again.",Toast.LENGTH_LONG).show();
                return false;
            }
        }catch (ExecutionException e1){
            Log.i("error","execution exception");
        }catch (InterruptedException e2){
            Log.i("error","interrupted exception");
        }

        // If valid.
        if (validID) {
            // Check whether the patient has already been assigned to another care provider
            //System.out.println("patient -> care provider's user ID: "+mPatient.getCareProvider());
            if (mPatient.getCareProvider().equals("")) {
                System.out.println("reaches here!!!!!!!!");

                // Fetch user data (Care Provider)
                CareProvider careProvider = UserDataController.loadCareProviderData(this);


                // Fetch user data (Patient)
                String mPatientUserID = mPatient.getUserID();
                ElasticsearchController.GetPatient getPatient = new ElasticsearchController.GetPatient();
                getPatient.execute(mPatientUserID);
                try{
                    mPatient = getPatient.get();
                }catch (ExecutionException e1){

                }catch (InterruptedException e2){

                }

                mPatient.updateCareProvider(careProvider.getUserID());
                UserDataController.savePatientData(this,mPatient);
                //System.out.println("yesyesyesyesyes");

                careProvider.addPatient(mPatient);
                UserDataController.saveCareProviderData(this, careProvider);
                //System.out.println("reaches here!!!!!!!!");

                return true;
            }
            else{
                Toast.makeText(this, "This patient has been assigned to a care provider already.", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }


    public void Add(View view) {
        if(ValidatePatientId()) {
            Toast.makeText(this, "Patient Added", Toast.LENGTH_SHORT).show();
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(AddPatientView.this, CareProviderHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent);
        }
        else{
            //Toast.makeText(this, "Patient Not Added", Toast.LENGTH_SHORT).show();
        }
    }
}
