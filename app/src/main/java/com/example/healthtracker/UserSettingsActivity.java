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
    Patient currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        uemail = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);
        loadCurrentUserData();
    }

    public void loadCurrentUserData(){
        currentUser = UserDataController.loadPatientData(this);
        uemail.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhone());
    }

    public void editUserInfo(){
        currentUser.updateUserInfo(uemail.getText().toString(), phone.getText().toString());
        UserDataController.savePatientData(this, currentUser);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSettings(View view) {
        editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
