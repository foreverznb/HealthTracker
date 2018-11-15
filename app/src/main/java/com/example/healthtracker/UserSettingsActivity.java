package com.example.healthtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

public class UserSettingsActivity extends AppCompatActivity {
    private static final String TAG = "Settings";
    String email;
    String phoneString;
    String userNameString;
    String isCaregiver;
    FirebaseAuth mAuth;
    private User mUser;
    List<User> userInfo;
    EditText userName;
    EditText uemail;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        mAuth = FirebaseAuth.getInstance();
        loadUserProfileData();
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //ref.orderByChild("users").startAt("someUser");


    }

    @Override
    protected void onStart(){
        super.onStart();
        /*if (mAuth.getCurrentUser() ==null){
            // might need to change this later
            startActivity(new Intent(this, LoginActivity.class));
        }*/
    }

    public void loadUserProfileData(){

        //TODO add load from local instead if offline

        userName = findViewById(R.id.edit_userid);
        uemail = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);


        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        email=user.getEmail();
        uemail.setText(email);



        DatabaseReference userDataRef = FirebaseDatabase.getInstance().getReference("users");
        userDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    if (Objects.equals(childSnap.child("email").getValue(), email)){
                        phoneString = childSnap.child("phone").getValue().toString();
                        userNameString = childSnap.child("userName").getValue().toString();
                        isCaregiver = childSnap.child("isCaregiver").getValue().toString();
                        // display data
                        phone.setText(phoneString);
                        userName.setText(userNameString);
                    }
                    // These are just allowing you to see what the system is getting from the data base. Can see the result by
                    // looking in the log cat under the verbose tab and typing tmz into the search bar
                    Log.v("tmz1", "" + childSnap.getKey()); //displays the key for the node
                    Log.v("tmz2", "" + childSnap.child("email").getValue());
                    Log.v("tmz3", "" + childSnap.child("phone").getValue());
                    Log.v("tmz4", "" + childSnap.child("userName").getValue()); //gives the value for given keyname
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void editUserInfo(){

        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        email = uemail.getText().toString().toLowerCase();
        String userID = user.getUid();

        // save locally
        if(isCaregiver.equals("true")){
            CareProviderDataManager dataManager = new CareProviderDataManager(this);
            CareProvider localUser = dataManager.loadCareProviderLocally();
            localUser.updateUserInfo(phoneString, email, userNameString);
            dataManager.saveCareProviderLocally(localUser);
        } else{
            PatientDataManager dataManager = new PatientDataManager(this);
            Patient localUser = dataManager.loadPatientLocally();
            localUser.updateUserInfo(phoneString, email, userNameString);
            dataManager.savePatientLocally(localUser);
        }

        //TODO add check for empty fields


        // save to dataBase
        DatabaseReference userDataRef = FirebaseDatabase.getInstance().getReference("users");
        user.updateEmail(email);
        userDataRef.child(userID).child("phone").setValue(phone.getText().toString());
        userDataRef.child(userID).child("userName").setValue(userName.getText().toString());
        userDataRef.child(userID).child("email").setValue(uemail.getText().toString().toLowerCase());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSettings(View view) {
        editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
