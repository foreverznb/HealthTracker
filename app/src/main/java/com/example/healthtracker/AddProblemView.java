package com.example.healthtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class AddProblemView extends AppCompatActivity {

    public EditText titleText;
    public EditText dateText;
    public EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
        titleText = findViewById(R.id.title_text);
        dateText = findViewById(R.id.date_started_editable);
        descriptionText = findViewById(R.id.problem_description_edit);
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

    public void returnFromAddProblem(View view) {
        EditText title = findViewById(R.id.title_text);
        EditText date = findViewById(R.id.date_started_editable);
        EditText description = findViewById(R.id.problem_description_edit);
        if (title.getText().toString().equals("") || date.getText().toString().equals("")
                || description.getText().toString().equals("")) {
            AlertDialog.Builder ab = new AlertDialog.Builder(AddProblemView.this);
            ab.setMessage("Warning. Changes have been made to the problem." + "\n" + "Returning to the home screen will not save changes.");
            ab.setCancelable(true);
            // Set a button to return to the Home screen and don't save changes
            ab.setNeutralButton("Cancel Problem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // launch an intent to return to the home screen
                    Intent intent = new Intent(AddProblemView.this, PatientHomeView.class);
                    startActivity(intent);
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
            Intent intent = new Intent(AddProblemView.this, PatientHomeView.class);
            // Return to the home activity
            startActivity(intent);

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
            Toast.makeText(this, "Problem Added", Toast.LENGTH_SHORT).show();
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(AddProblemView.this, PatientHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent);
        }
    }

    public void saveProblem(){

        String title = titleText.getText().toString();
        String dateString = dateText.getText().toString();
        String description = descriptionText.getText().toString();
        Date date = null;
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e){
            Toast.makeText(this, "Improper Date Format", Toast.LENGTH_LONG).show();
        }
        Problem problem = new Problem(title, date, description);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    }


    // add record button
    public void addRecordFromAdd(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddProblemView.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}