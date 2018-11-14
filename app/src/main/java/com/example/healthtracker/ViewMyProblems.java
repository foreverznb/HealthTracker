package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewMyProblems extends AppCompatActivity {

    User currentUser;
    String username;
    List<Problem> userProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_problems);


        // update problems if they are ever changed
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference problemRef = ref.child("users").child(username).child("problems");

        problemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProblems = dataSnapshot.getValue(List.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
        Intent intent = new Intent(ViewMyProblems.this, PatientHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }





}
