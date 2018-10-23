package com.example.healthtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ViewMyProblems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_problems);


        /*
        // add listener to detect button click on items in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // method to initiate after listener detects click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create an alert dialog via the alert dialog builder to help build dialog to specifics
                AlertDialog.Builder ab = new AlertDialog.Builder(ViewMyProblems.this);
                // set dialog message to edit entry to appear at grabbed position
                ab.setMessage("Problem Options:" + po.get(position).toString() + "\n");
                // set the dialog to be cancelable outside of box
                ab.setCancelable(true);
                final int fPosition = position;


                // set a negative button for deleting emotion entries on click via the listener
                ab.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // set a neutral button in the dialog which will open up the edit entry activity to modify the emotion
                ab.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    // Create an intent object containing the bridge to between the two activities
                    Intent intent = new Intent(ViewMyProblems.this, EditProblem.class);
                    // Launch the browse emotions activity
                    startActivity(intent);

                    }
                });

                // required in order for dialog object to appear on screen
                ab.show();
            }
        });*/
    }

    public void returnFromMyProblems(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(ViewMyProblems.this, PatientHome.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }









}
