package com.example.healthtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;


public class CreateAccountActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";
    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    private ArrayAdapter<Patient> adapter;

    private static JestDroidClient client;

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText Email, Password, Phone, Username;
    private Button Register;


    private CreateAccountActivity Context;
    private String email, password, phone, username;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Register = findViewById(R.id.create_new_account_button);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.new_password);
        Phone = findViewById(R.id.phone_number);
        Username = findViewById(R.id.username);
        Context = CreateAccountActivity.this;
        Log.d(TAG, "onCreate: started");
        init();
    }

    private void init(){
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString().toLowerCase();
                password = Password.getText().toString();
                phone = Phone.getText().toString();
                username = Username.getText().toString();

                if (checkInputs(email, username, password, phone)) {
                    addNewUser();
                    finish();
                }
                else{
                    Toast.makeText(Context, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*
     * Checks all the input fields for null
     */
    private boolean checkInputs(String email, String username, String password, String phone){
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if(email.equals("") || username.equals("") || password.equals("") || phone.equals("")){
            Toast.makeText(Context, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


   /* private void setupFirebaseAuth(){

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

    }*/

    public void addNewUser(){

        // just add generic user for now
        // saves user online...I hope
        User user = new User(phone, email, username);
        ElasticUserController.AddUser addUserTask = new ElasticUserController.AddUser();
        addUserTask.execute(user);


    }

    public void test(){
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.new_password);
        EditText userName=findViewById(R.id.username);
        EditText phone=findViewById(R.id.phone_number);
        String nuserName = userName.getText().toString();
        String nPhone = phone.getText().toString();
        String nPassword = password.getText().toString();
        String nemail = email.getText().toString();

        Patient newPatient = new Patient(nPhone, nemail, nuserName);
        patientList.add(newPatient);
        //adapter.notifyDataSetChanged();
        //saveInFile(); // TODO replace this with elastic search
        ElasticUserController.AddUser addNewUser = new ElasticUserController.AddUser();
        addNewUser.execute(newPatient);

    }
}