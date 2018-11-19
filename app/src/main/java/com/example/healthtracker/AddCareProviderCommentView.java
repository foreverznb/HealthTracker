package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * AddCareProviderCommentView enables a CareProvider to create a comment record that will be added
 * to a patients problem. The patient and problem will have been selected before starting this
 * activity. The careprovider can fill in a title and comment for the record in textviews and save the
 * record by selecting the save button.
 *
 * @author
 * @version 1.0
 * @since 2018-10-30
 */
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
