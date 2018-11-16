package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

// extends
public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";

    private EditText Email, Password;
    private String isCaregiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set screen to layout specified in activity_main
        setContentView(R.layout.activity_login);
        //mRegister = (TextView) findViewById(R.id.link_register);

        Email = findViewById(R.id.username);
        Password = findViewById(R.id.login_password);
    }


    public void UserLogin(View view) throws ExecutionException, InterruptedException {
        Email = findViewById(R.id.username);
        String email = Email.getText().toString();
        if (!isEmpty(Email.getText().toString()) && !isEmpty(Password.getText().toString())) {
            Patient patient;
            ElasticUserController.GetPatient getPatient = new ElasticUserController.GetPatient();
            getPatient.execute(email);
            patient = getPatient.get();
            if (patient != null) {
                Intent intent = new Intent(LoginActivity.this, PatientHomeView.class);
                // Launch the browse emotions activity
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Unknown Account", Toast.LENGTH_SHORT).show();
            }



        } else {
            Toast.makeText(LoginActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isEmpty(String string){
        return string.equals("");
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void CreateAccount(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    public void Login() {

    }

}