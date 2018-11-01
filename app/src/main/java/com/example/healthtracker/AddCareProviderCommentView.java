package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddCareProviderCommentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_care_provider_comment);
    }

    public void returnToProblem(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddCareProviderCommentView.this, CareProviderProblemView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void saveCareProviderComment(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddCareProviderCommentView.this, CareProviderProblemView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
