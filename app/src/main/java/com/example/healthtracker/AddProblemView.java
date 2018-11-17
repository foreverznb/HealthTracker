package com.example.healthtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class AddProblemView extends AppCompatActivity {

    public EditText titleText;
    public EditText dateText;
    public EditText descriptionText;
    private DatabaseReference mDatabaseReference;
    public static Integer counter = 0;
    String title;
    String dateString;
    String description;
    Date date;
    PatientDataManager dataManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
        titleText = findViewById(R.id.title_text);
        dateText = findViewById(R.id.date_started_editable);
        descriptionText = findViewById(R.id.problem_description_edit);
        dataManager = new PatientDataManager(this);
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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


        //download user if online or get locally if online here
        //only online implemented for now
        String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        final DatabaseReference UserDataRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
        UserDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // get data
                Patient currentUser = dataSnapshot.getValue(Patient.class);

                // add data
                Problem problem = new Problem(title, date, description);
            //    currentUser.addProblem(problem);

                // save locally
                dataManager.savePatientLocally(currentUser);

                // save to database
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    dataManager.savePatientToDatabase(currentUser);
                }

                // finish adding problem
                Toast.makeText(context, "Problem Added", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    // add record button
    public void addRecordFromAdd(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(AddProblemView.this, AddorEditRecordView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }
}