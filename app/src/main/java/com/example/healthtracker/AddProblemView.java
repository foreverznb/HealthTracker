package com.example.healthtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddProblemView extends AppCompatActivity {

    public EditText titleText;
    public EditText dateText;
    public EditText descriptionText;
    public static Integer counter = 0;
    String title;
    String dateString;
    String description;
    Date date;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
        titleText = findViewById(R.id.title_text);
        dateText = findViewById(R.id.date_started_editable);
        descriptionText = findViewById(R.id.problem_description_edit);
        context = this;
    }

    private static boolean testDate(String date) {
        // establish the date format and make the format non lenient, include a parse catch and try clause
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        format.setLenient(false);
        try {
            format.parse(date.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!titleText.getText().toString().equals("") || !dateText.getText().toString().equals("")
                || !descriptionText.getText().toString().equals("")) {
            AlertDialog.Builder ab = new AlertDialog.Builder(AddProblemView.this);
            ab.setMessage("Warning. Changes have been made to the problem." + "\n" + "Returning to the home screen will not save changes.");
            ab.setCancelable(true);
            // Set a button to return to the Home screen and don't save changes
            ab.setNeutralButton("Exit And Lose Changes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            // set a button which will close the alert dialog
            ab.setNegativeButton("Return to Problem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            // show the alert dialog on the screen
            ab.show();
        } else {
            finish();
        }
    }


    // save button
    public void addPatientProblem(View view) {
        if (titleText.getText().toString().equals("") || dateText.getText().toString().equals("")
                || descriptionText.getText().toString().equals("")) {
            Toast.makeText(this, "Error, all field must be filled", Toast.LENGTH_LONG).show();
        } else if (!testDate(dateText.getText().toString())) {
            Toast.makeText(this, "Improper Date Format", Toast.LENGTH_LONG).show();
        } else {
            saveProblem();
        }
    }

    public void saveProblem(){

        // get Problem info
        title = titleText.getText().toString();
        dateString = dateText.getText().toString();
        description = descriptionText.getText().toString();
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e){
            Toast.makeText(this, "Improper Date Format", Toast.LENGTH_LONG).show();
        }

        // fetch user data
        Patient patient = UserDataController.loadPatientData(context);

        // create problem
        patient.addProblem(new Problem(title, date, description));

        // save problem
        UserDataController.savePatientData(context, patient);

        // done
        finish();

    }


    // add record button
    public void addRecordFromAdd(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddProblemView.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}