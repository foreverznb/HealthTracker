package com.example.healthtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;


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
    String email;
    private Patient patient;
    String userID;
    List<User> userInfo;
    EditText uemail;
    private CareProvider careProvider;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        uemail = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);
        try {
            loadUserProfileData();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadUserProfileData() throws ExecutionException, InterruptedException {
        //TODO add load from local instead if offline

        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        userID = pref.getString("userID", "error");

        // get the info of the patient stored in shared preferences from ElasticSearch

        ElasticUserController.GetPatient getPatient = new ElasticUserController.GetPatient();
        getPatient.execute(userID);
        patient = getPatient.get();
        uemail.setText(patient.getEmail());
        phone.setText(patient.getPhone());

    }

    public void editUserInfo(){
        patient.setEmail(uemail.getText().toString());
        patient.setPhone(phone.getText().toString());

        Patient newPatient = new Patient(phone.getText().toString(), uemail.getText().toString(), userID);
        ElasticUserController.AddPatient addPatientTask = new ElasticUserController.AddPatient();
        addPatientTask.execute(newPatient);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSettings(View view) {
        editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
