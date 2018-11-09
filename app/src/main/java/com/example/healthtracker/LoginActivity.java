
package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

// extends
public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<User> userList =new ArrayList<User>();
    private ArrayAdapter<User> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set screen to layout specified in activity_main
        setContentView(R.layout.activity_login);
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void CreateAccount(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Login(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        EditText userId=findViewById(R.id.username);
        String username=userId.getText().toString();
        ElasticSearch.GetUser getUser = new ElasticSearch.GetUser();
        getUser.execute(username);
        if (checkBox.isChecked()) {
            // Create an intent object containing the bridge to between the two activities
            Intent intent = new Intent(LoginActivity.this, CareProviderHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent);
        }

        else {
            // Create an intent object containing the bridge to between the two activities
            Intent intent2 = new Intent(LoginActivity.this, PatientHomeView.class);
            // Launch the browse emotions activity
            startActivity(intent2);
        }
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            userList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            userList = new ArrayList<User>();
        }
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(userList, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}