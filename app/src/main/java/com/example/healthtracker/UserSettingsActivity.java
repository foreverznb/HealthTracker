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
    List<User> userInfo;
    EditText userName;
    EditText uemail;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        try {
            loadUserProfileData();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadUserProfileData() {
        //TODO add load from local instead if offline
        uemail = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);

        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String userName2 = pref.getString("username", "error");

        // get the info of the patient stored in shared preferences from ElasticSearch
        ElasticsearchController.GetPatient getPatient = new ElasticsearchController.GetPatient();
        getPatient.execute(userName2);
        try {
            patient = getPatient.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        userName.setText(userName2);
        uemail.setText(patient.getEmail());
        phone.setText(patient.getPhone());
    }

    public void editUserInfo(){
        patient.setEmail(uemail.getText().toString());
        patient.setPhone(phone.getText().toString());

        Patient newPatient = new Patient(phone.getText().toString(), uemail.getText().toString(), userName.getText().toString());
        ElasticsearchController.AddPatient addPatientTask = new ElasticsearchController.AddPatient();
        addPatientTask.execute(newPatient);

        /*
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", userName.getText().toString()); // Storing string
        editor.apply();*/
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSettings(View view) {
        editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
