package com.example.healthtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProblem extends AppCompatActivity {


    private EditText title;
    private EditText dateText;
    private EditText description;
    private String initial_entry;
    private Patient user;
    private Problem problem;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        title = findViewById(R.id.title_text_editscreen);
        dateText = findViewById(R.id.date_started_editscreen);
        description= findViewById(R.id.problem_description_editscreen);

        // get current problem data
        user = UserDataController.loadPatientData(this);
        Intent intent = getIntent();
        index = intent.getIntExtra("Index", -1);
        assert index >= 0;
        problem = user.getProblem(index);

        // display current data
        displayData();

        initial_entry = getEntry();
    }

    public void displayData(){
        title.setText(problem.getTitle());
        description.setText(problem.getDescription());
        Date date = problem.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        String dateString = format.format(date);
        dateText.setText(dateString);
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
        return title.getText().toString() + " -- " + dateText.getText().toString() + "\n" + description.getText().toString();
    }

    @Override
    public void onBackPressed() {
        if (!initial_entry.equals(getEntry())) {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
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

    public void editPatientProblem(View view) {
        if (title.getText().toString().equals("") || dateText.getText().toString().equals("")
                || description.getText().toString().equals("")) {
            Toast.makeText(this, "Error, all fields must be filled", Toast.LENGTH_LONG).show();
        }
        else if(!testDate(dateText.getText().toString())){
            Toast.makeText(this, "Improper Date Format", Toast.LENGTH_LONG).show();
        }
        else {
            // get changes
            String titleString = title.getText().toString();
            String descriptionString = description.getText().toString();
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            try {
                date = format.parse(dateText.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // save changes
            user = UserDataController.loadPatientData(this);
            problem = user.getProblem(index);
            problem.update(titleString, date, descriptionString);
            user.setProblem(problem, index);
            UserDataController.savePatientData(this, user);

            // done
            finish();
        }

    }

    public void addRecordFromEdit(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(EditProblem.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}