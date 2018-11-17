package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewMyProblems extends AppCompatActivity {

    /*
    User currentUser;
    String username;
    List<Problem> problems;
    */

    private ArrayList<Problem> mProblems = new ArrayList<Problem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_problems);

        // Create an instance of an array adapter
        final ArrayAdapter<Problem> mArrayAdapter = new ArrayAdapter<Problem>(this, android.R.layout.simple_list_item_1, mProblems);

        // Set an adapter for the list view
        ListView mListView = findViewById(R.id.problem_list_view);
        mListView.setAdapter(mArrayAdapter);

        // Read from the real time database to obtain problems created by the Patient

        // Retrieve an instance of the database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Getting reference

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("problems").child(uid);

        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    mProblems.add(dss.getValue(Problem.class));
                    Log.d("my problem", dss.getValue(Problem.class).toString());
                    mArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Failed to read value", databaseError.toException());
            }
        });

        // Create a context menu to permit users to select and edit a problem
        registerForContextMenu(mListView);
        mListView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        menu.setHeaderTitle("Select an action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("message","aaaa");
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Problem mProblem = mProblems.get(index);
        if(item.getItemId() == R.id.editProblem){
            Log.d("message","bbbbb");
            Toast.makeText(getApplicationContext(),"edit a problem",Toast.LENGTH_LONG);
            Intent intent = new Intent(ViewMyProblems.this,EditProblem.class);
            startActivity(intent);
        }
        return true;
    }

    /*

        */
/*
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

*/
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
        });
    }
    */

    public void returnFromMyProblems(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(ViewMyProblems.this, PatientHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }





}
