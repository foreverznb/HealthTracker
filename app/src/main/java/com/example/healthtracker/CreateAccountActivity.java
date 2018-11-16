package com.example.healthtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.searchly.jestdroid.JestDroidClient;


public class CreateAccountActivity extends AppCompatActivity {

    private static JestDroidClient client;

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText Email, Password, Phone, UserID;
    private Button Register;


    private CreateAccountActivity Context;
    private String email, password, phone, userID;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Register = findViewById(R.id.create_new_account_button);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.new_password);
        Phone = findViewById(R.id.phone_number);
        UserID = findViewById(R.id.userID);
        Context = CreateAccountActivity.this;
        Log.d(TAG, "onCreate: started");
        init();
    }

    private void init(){
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString().toLowerCase();
                password = Password.getText().toString();
                phone = Phone.getText().toString();
                userID = UserID.getText().toString();

                if (checkInputs(email, userID, password, phone)) {
                    addNewUser();
                    Toast.makeText(Context, "Account created", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Context, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
     * Checks that all the input fields are filled
     */
    // need to add more checks for email and password
    private boolean checkInputs(String email, String userID, String password, String phone){
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if(email.equals("") || userID.equals("") || password.equals("") || phone.equals("")){
            Toast.makeText(Context, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void addNewUser(){
        CheckBox checkBox = findViewById(R.id.caregiver_checkbox);
        // Save new user with elasticsearch
        if(checkBox.isChecked()){
            // save new care provider
            CareProvider newCareProvider = new CareProvider(phone, email, userID);
            ElasticUserController.AddCareProvider addCareProviderTask = new ElasticUserController.AddCareProvider();
            addCareProviderTask.execute(newCareProvider);
        } else{
            // save new patient
            Patient newPatient = new Patient(phone, email, userID);
            ElasticUserController.AddPatient addPatientTask = new ElasticUserController.AddPatient();
            addPatientTask.execute(newPatient);
        }



    }
}