package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


/**
 * UserSettingsActivity enables users to modify their account information. The changes to their local account are modified in the
 * ElasticSearch database as well as stored locally after adjustment.
 *
 * @author Tyler Watson
 * @version 1.0
 * @since 2018-11-12
 */
public class UserSettingsActivity extends AppCompatActivity {
    private static final String TAG = "Settings";
    String userID;
    String profileType;
    List<User> userInfo;
    EditText uemail;
    EditText phone;
    private Patient patient;
    private CareProvider careProvider;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        uemail = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);
        Intent intent = getIntent();
        profileType = intent.getStringExtra("profileType");
        if(profileType.equals("Patient")){
            loadCurrentPatientData();
        } else{
            loadCurrentCareProviderData();
        }
    }

    public void loadCurrentPatientData(){
        patient = UserDataController.loadPatientData(this);
        uemail.setText(patient.getEmail());
        phone.setText(patient.getPhone());
    }

    public void loadCurrentCareProviderData(){
        careProvider = UserDataController.loadCareProviderData(this);
        uemail.setText(careProvider.getEmail());
        phone.setText(careProvider.getPhone());
    }


    public void editUserInfo(){
        if(profileType.equals("Patient")){
            patient.updateUserInfo(uemail.getText().toString(), phone.getText().toString());
            UserDataController.savePatientData(this, patient);
        } else{
            careProvider.updateUserInfo(uemail.getText().toString(), phone.getText().toString());
            UserDataController.saveCareProviderData(this, careProvider);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void toHome() {
        //editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    /*
    public void loadData() {
        if (ElasticUserController.testConnection(context)) {
            if (profileType.equals("CareProvider")) {
                UserDataController.loadCareProviderData(this);
            } else {
                UserDataController.loadPatientData(this);
            }
        } else {
            Toast.makeText(context, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveData(View view) {
        if (ElasticUserController.testConnection(context)) {
            if (isEmpty(uemail.getText().toString()) && isEmpty(phone.getText().toString())) {
                // if intent extra was careprovider
                if (profileType.equals("CareProvider")) {
                    UserDataController.saveCareProviderData(this, careProvider);
                    toHome();
                } else {
                    UserDataController.savePatientData(this, patient);
                    toHome();

                }
            } else {
                Toast.makeText(UserSettingsActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }*/

    /**
     * isEmpty() tests if one of edit account detail features is not filled in
     *
     * @param string string provided on method call to be tested if it is blank
     * @return returns a boolean object on whether the provided string is blank
     */
    private boolean isEmpty(String string) {
        return !string.equals("");
    }

    /**
     * testConnection() checks for online connectivity on either wifi or mobile data and returns the connectivity state
     *
     * @return returns a boolean object on whether the user is connected to wifi or cellular data for online connectivity checks
     */

}
