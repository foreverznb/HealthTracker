package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    public void Return(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Save(View view) {
        // Display a brief message on screen upon the browse emotions button being clicked
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
