package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
    }



    /*
        // add listener to detect button click on items in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // method to initiate after listener detects click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an intent object containing the bridge to between the two activities
                Intent intent = new Intent(ViewUsers.this, ViewAUser.class);
                // Launch the browse emotions activity
                startActivity(intent);
                }
            }*/

    public void Return4(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(ViewUsers.this, CareProviderHome.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}
