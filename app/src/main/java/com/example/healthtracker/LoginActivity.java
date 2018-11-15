package com.example.healthtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;

// extends
public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";

    FirebaseAuth mAuth;
    private EditText Email, Password;
    private String isCaregiver;
    DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set screen to layout specified in activity_main
        setContentView(R.layout.activity_login);
        //mRegister = (TextView) findViewById(R.id.link_register);

        Email = findViewById(R.id.username);
        Password = findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();

    }


    public void UserLogin(View view) {
        if (!isEmpty(Email.getText().toString()) && !isEmpty(Password.getText().toString())) {
            mAuth.signInWithEmailAndPassword(Email.getText().toString().toLowerCase(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Login();
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
        // firebase set up
        FirebaseUser user = mAuth.getCurrentUser();
        final String userid = user.getUid();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        // access data of current user
        userRef = reference.child(userid);

        // managers to cache data locally
        final PatientDataManager patientManager = new PatientDataManager(this);
        final CareProviderDataManager providerDataManager = new CareProviderDataManager(this);


        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // verify is user is a caregiver or patient
                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    if(childSnap.getKey().equals("isCaregiver")){
                        isCaregiver = childSnap.getValue().toString();
                    }
                }
                if (isCaregiver.equals("true")) {
                    // get user data and save locally
                    CareProvider activeCareProvider = dataSnapshot.getValue(CareProvider.class);
                    providerDataManager.saveCareProviderLocally(activeCareProvider);

                    // Create an intent object containing the bridge to between the two activities
                    Intent intent = new Intent(LoginActivity.this, CareProviderHomeView.class);
                    // Launch the activity
                    startActivity(intent);
                }

                else {
                    // get user data and save locally
                    Patient activePatient = dataSnapshot.getValue(Patient.class);
                    patientManager.savePatientLocally(activePatient);

                    // Create an intent object containing the bridge to between the two activities
                    Intent intent2 = new Intent(LoginActivity.this, PatientHomeView.class);
                    // Launch the activity
                    startActivity(intent2);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*
    // handle if user is already logged in to bypass the login screen
    @Override
    protected void onStart(){
        super.onStart();
        if (mAuth.getCurrentUser() !=null){
            //finish();
            // if user is a patient

            // if user is a care provider
        }
    }*/


}