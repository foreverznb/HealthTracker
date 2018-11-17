package com.example.healthtracker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.searchly.jestdroid.JestDroidClient;

import java.util.concurrent.ExecutionException;


public class CreateAccountActivity extends AppCompatActivity {

    private static JestDroidClient client;

    private static final String TAG = "CreateAccountActivity";
    private EditText Email, Password, Phone, UserID;
    private Button Register;
    private CheckBox checkBox;
    private Context context;


    private CreateAccountActivity Context;
    private String email, password, phone, userID;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Register = findViewById(R.id.create_new_account_button);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.new_password);
        Phone = findViewById(R.id.phone_number);
        UserID = findViewById(R.id.userID);
        checkBox = findViewById(R.id.caregiver_checkbox);
        context = this;
        Log.d(TAG, "Testing Internet Connection");
        if (testConnection()) {
            Toast.makeText(context, "No fucking internet fuck", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "We gots the wifi", Toast.LENGTH_SHORT).show();
        }
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
                    try {
                        if(!userExists(userID)){
                            addNewUser();
                            finish();
                        } else{
                            Toast.makeText(context, "User ID is taken", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean userExists(String userID) throws ExecutionException, InterruptedException {
        if(checkBox.isChecked()){
            CareProvider foundUser;
            ElasticUserController.GetCareProvider getCareProvider = new ElasticUserController.GetCareProvider();
            getCareProvider.execute(userID);
            foundUser = getCareProvider.get();
            return foundUser != null;
        } else{
            Patient foundUser;
            ElasticUserController.GetPatient getPatient = new ElasticUserController.GetPatient();
            getPatient.execute(userID);
            foundUser = getPatient.get();
            return foundUser != null;
        }

    }


    public void addNewUser(){
        if (testConnection()) {
            Toast.makeText(context, "No fucking internet fuck", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "We gots the wifi", Toast.LENGTH_SHORT).show();
        }
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

        try {
            if(userExists(userID)){
                Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(context, "Failed to create account. Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean testConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //we are connected to a network
        assert connectivityManager != null;
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.CONNECTED &&
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.CONNECTED;
    }
}