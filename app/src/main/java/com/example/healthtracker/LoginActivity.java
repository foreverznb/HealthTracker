
package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

// extends
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set screen to layout specified in activity_main
        setContentView(R.layout.activity_login);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void CreateAccount(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Login(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        if (checkBox.isChecked()) {
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(LoginActivity.this, CareProviderHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent);
        }

        else {
            // Create an intent object containing the bridge to between the two activities
            Intent intent2 = new Intent(LoginActivity.this, PatientHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent2);
        }
    }
}