package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class UserSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
    }

    public void Logout(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(UserSettingsActivity.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void saveSettings(View view) {
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(UserSettingsActivity.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void returnHome(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(UserSettingsActivity.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
