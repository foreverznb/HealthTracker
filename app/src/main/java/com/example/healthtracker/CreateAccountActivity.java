package com.example.healthtracker;

import android.content.SharedPreferences;
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
    private EditText Email, Password, Phone, Username;
    private Button Register;


    private CreateAccountActivity Context;
    private String email, password, phone, username;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Register = findViewById(R.id.create_new_account_button);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.new_password);
        Phone = findViewById(R.id.phone_number);
        Username = findViewById(R.id.username);
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
                username = Username.getText().toString();

                if (checkInputs(email, username, password, phone)) {
                    addNewUser();
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
    private boolean checkInputs(String email, String username, String password, String phone){
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if(email.equals("") || username.equals("") || password.equals("") || phone.equals("")){
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
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username); // Storing string
            editor.apply();

            CareProvider newCareProvider = new CareProvider(phone, email, username);
            ElasticUserController.AddCareProvider addCareProviderTask = new ElasticUserController.AddCareProvider();
            addCareProviderTask.execute(newCareProvider);
        } else{
            // save new patient
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username); // Storing string
            editor.apply();

            Patient newPatient = new Patient(phone, email, username);
            ElasticUserController.AddPatient addPatientTask = new ElasticUserController.AddPatient();
            addPatientTask.execute(newPatient);
        }



    }
}