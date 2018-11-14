package com.example.healthtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmail, mPassword, mPhone, mUsername;
    private Button mRegister;


    private CreateAccountActivity mContext;
    private String email, password, phone, username;
    private User mUser;


    public void Return(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Save(View view) {
        addNewUser();
        // Display a brief message on screen upon the browse emotions button being clicked
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mRegister = findViewById(R.id.create_new_account_button);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.new_password);
        mPhone = findViewById(R.id.phone_number);
        mUsername = findViewById(R.id.username);
        mContext = CreateAccountActivity.this;
        mUser = new User();
        Log.d(TAG, "onCreate: started");
        setupFirebaseAuth();
        init();
    }

    private void init(){
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                phone = mPhone.getText().toString();
                username = mUsername.getText().toString();

                if (checkInputs(email, username, password, phone)) {
                    registerNewEmail(email, password);}
                else{
                    Toast.makeText(mContext, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    /**
     * Checks all the input fields for null
     * @param email
     * @param username
     * @param password
     * @param phone
     * @return
     */
    private boolean checkInputs(String email, String username, String password, String phone){
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if(email.equals("") || username.equals("") || password.equals("") || phone.equals("")){
            Toast.makeText(mContext, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




      /*
    ---------------------------Firebase-----------------------------------------
     */


    private void setupFirebaseAuth(){

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is authenticated
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out");

                }
                // ...
            }
        };

    }

    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     */
    public void registerNewEmail(final String email, String password){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "registerNewEmail: onComplete: " + task.isSuccessful());

                        if (task.isSuccessful()){
                            //add user details to firebase database
                            Toast.makeText(mContext, "Account created.",
                                    Toast.LENGTH_SHORT).show();
                            addNewUser();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, "Email or password are invalid.",
                                    Toast.LENGTH_SHORT).show();
                            // email taken, email invalid, or password too short end up here
                        }
                        // TODO: check for valid phone#, possibly set restrictions on password length
                    }
                });
    }

    /**
     * Adds data to the node: "users"
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addNewUser(){

        //add data to the "users" node
        String userid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        Log.d(TAG, "addNewUser: Adding new User: \n user_id:" + userid);
        mUser.setUserName(username);
        mUser.setUserID(userid);
        mUser.setEmail(email);
        mUser.setPhone(phone);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        //insert into users node
        reference.child("users").child(username).setValue(mUser);

        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}