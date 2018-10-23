package com.example.healthtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EditProblem extends AppCompatActivity {

    private String initial_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        initial_entry = getEntry();
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

    private String getEntry() {
        EditText title = findViewById(R.id.title_text);
        EditText date = findViewById(R.id.date_started_editable);
        EditText description= findViewById(R.id.problem_description_edit);
        return title.getText().toString() + " -- " + date.getText().toString() + "\n" + description.getText().toString();
    }

    public void returnFromEditProblem(View view) {
        if (!initial_entry.equals(getEntry())) {
            AlertDialog.Builder ab = new AlertDialog.Builder(EditProblem.this);
            ab.setMessage("Warning. Changes have been made." + "\n" + "Would you like to save these changes?.");
            ab.setCancelable(true);
            // Set a button to return to the Home screen and don't save changes
            ab.setNeutralButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // launch an intent to return to the home screen
                    Intent intent = new Intent(EditProblem.this, PatientHome.class);
                    startActivity(intent);
                }
            });

            // set a button which will close the alert dialog
            ab.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            // show the alert dialog on the screen
            ab.show();
        }
        else{
            Intent intent = new Intent(EditProblem.this, ViewMyProblems.class);
            // Return to the home activity
            startActivity(intent);

        }
    }

    public void editPatientProblem(View view) {
        EditText title = findViewById(R.id.title_text);
        EditText date = findViewById(R.id.date_started_editable);
        EditText description= findViewById(R.id.problem_description_edit);
        if (title.getText().toString().equals("") || date.getText().toString().equals("")
                || description.getText().toString().equals("")) {
            Toast.makeText(this, "Error, all field must be filled", Toast.LENGTH_LONG).show();
        }
        else if(!testDate(date.getText().toString())){
            Toast.makeText(this, "Improper Date Format", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Problem Edited", Toast.LENGTH_SHORT).show();
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(EditProblem.this,ViewMyProblems.class);
            // Launch the browse emotions activity
            startActivity(intent);
        }
    }

    public void addRecordFromEdit(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(EditProblem.this, AddorEditRecord.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}