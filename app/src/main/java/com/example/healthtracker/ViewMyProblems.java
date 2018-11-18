package com.example.healthtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMyProblems extends AppCompatActivity {

    /*
    User currentUser;
    String username;
    List<Problem> problems;
    */

    private ArrayList<Problem> mProblems;
    private ListView mListView;
    private Context context;
    private ArrayAdapter<Problem> mArrayAdapter;
    private Patient user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_problems);

        // get context
        context = this;

    }

    @Override
    public void onResume(){
        super.onResume();

        // Load patient data
        user = UserDataController.loadPatientData(this);
        mProblems = user.getProblemList();

        // Create an instance of an array adapter
        mArrayAdapter = new ArrayAdapter<Problem>(this, android.R.layout.simple_list_item_1, mProblems);

        // Set an adapter for the list view
        mListView = findViewById(R.id.problem_list_view);
        mListView.setAdapter(mArrayAdapter);

        // Create a context menu to permit users to select and edit a problem
        registerForContextMenu(mListView);
        mListView.setOnCreateContextMenuListener(this);

        // Add listener to detect button click on items in listview
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // method to initiate after listener detects click
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create an alert dialog via the alert dialog builder to help build dialog to specifics
                AlertDialog.Builder ab = new AlertDialog.Builder(ViewMyProblems.this);
                // set dialog message to edit entry to appear at grabbed position
                ab.setMessage("Problem Options:" + mProblems.get(position).toString() + "\n");
                // set the dialog to be cancelable outside of box
                ab.setCancelable(true);
                final int fPosition = position;


                // set a negative button for deleting emotion entries on click via the listener
                ab.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete problem
                        mProblems.remove(position);
                        user.setProblems(mProblems);
                        UserDataController.savePatientData(context, user);

                        // update listview
                        mArrayAdapter.notifyDataSetChanged();

                        // done
                        dialog.dismiss();

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

                        // store problem index
                        intent.putExtra("Index", position);

                        // Launch the browse emotions activity
                        startActivity(intent);
                    }
                });

                // required in order for dialog object to appear on screen
                ab.show();
            }
        });

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

}
