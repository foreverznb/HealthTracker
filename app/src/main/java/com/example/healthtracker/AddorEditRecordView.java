package com.example.healthtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddorEditRecordView extends AppCompatActivity {

    public EditText titleText;
    public EditText descriptionText;
    String title;
    String comment;
    Context context;
    Patient patient;
    PatientRecord record;

    /*TODO cite https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
    for start/end intent for result
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addor_edit_record);
        titleText = findViewById(R.id.title_edit_text);
        descriptionText = findViewById(R.id.description_edit_text);
        context = this;
        record = new PatientRecord();

        // if editing a record show its current details
        Intent intent = getIntent();
        int index = intent.getIntExtra("Index", -1);
        if (index != -1) {
            String recordString = intent.getStringExtra("Record");
            record = UserDataController.unSerializeRecord(this, recordString);
            showRecord();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Warning. You are about to go back without saving the record.");
        ab.setCancelable(true);
        // Set a button to return to the Home screen and don't save changes
        ab.setNeutralButton("Exit And Lose Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        // set a button which will close the alert dialog
        ab.setNegativeButton("Return to Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // show the alert dialog on the screen
        ab.show();
    }

    public void saveButton(View view) {
        addRecord();
    }

    public void showRecord(){
        titleText.setText(record.getTitle());
        descriptionText.setText(record.getComment());
        //TODO show geomap, photos, bodlocation

    }

    public void addRecord() {
        if (titleText.getText().toString().equals("")) {
            Toast.makeText(this, "Error, a title is required.", Toast.LENGTH_LONG).show();
        } else {
            saveRecord();
        }
    }

    public void saveRecord(){

        // get Record info
        title = titleText.getText().toString();
        comment = descriptionText.getText().toString();

        // fetch user data
        patient = UserDataController.loadPatientData(context);


        // add record
        record.setComment(comment);
        record.setTitle(title);
        record.setTimestamp();
        // TODO set photos, geomap, bodylocation once they are implemented

        // return to add problem with record result
        Intent intent = getIntent();
        intent.putExtra("Record", UserDataController
                .serializeRecord(AddorEditRecordView.this, record));
        setResult(RESULT_OK, intent);
        finish();

    }

}
