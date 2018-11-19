package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CareProviderProblemView extends AppCompatActivity {

    private TextView titleText;
    private TextView dateText;
    private TextView desText;
    private TextView recordText;

    private String title;
    private Date date;
    private String des;
    private ArrayList<PatientRecord> records;

    private ArrayAdapter<PatientRecord> rArrayAdapter;

    private Patient myPatient;
    private Problem pProblem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_problem_view);

        titleText = findViewById(R.id.viewTitle);
        dateText = findViewById(R.id.viewDate);
        desText = findViewById(R.id.viewDes);
        recordText = findViewById(R.id.viewRecord);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        CareProvider careProvider = UserDataController.loadCareProviderData(this);

        int patientNum = bd.getInt("patientNum");
        int problemNum = bd.getInt("problemNum");
        myPatient = careProvider.getPatientList().get(patientNum);
        pProblem = myPatient.getProblem(problemNum);

        title = pProblem.getTitle();
        date = pProblem.getDate();
        des = pProblem.getDescription();
        records = pProblem.getRecords();

        showProblem(title,date,des,records);



    }

    public String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        String dateString = format.format(date);
        return dateString;
    }

    public void showProblem(String title, Date date, String des, ArrayList<PatientRecord> records) {
        titleText.setText(title);
        dateText.setText(dateToString(date));
        desText.setText(des);
        //recordText;
    }

    public void returnToProblemsList(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, ViewPatientsProblems.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void addCareProviderComment(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, AddCareProviderCommentView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void viewProblemsPhotos(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CareProviderProblemView.this, SlideShowActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

}
