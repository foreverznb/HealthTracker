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
    FirebaseAuth mAuth;
    private User mUser;
    List<User> userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);// update problems if they are ever changed
        mAuth = FirebaseAuth.getInstance();
        loadUserProfileData();

    }

    @Override
    protected void onStart(){
        super.onStart();
        if (mAuth.getCurrentUser() ==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public void loadUserProfileData(){
        final EditText userName = findViewById(R.id.edit_userid);
        EditText uemail = findViewById(R.id.edit_email);
        final EditText phone = findViewById(R.id.edit_phone);


        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        final String uEmail = user.getEmail();
        uemail.setText(uEmail);
        email=user.getEmail();



        FirebaseDatabase ref = FirebaseDatabase.getInstance();
        DatabaseReference userDataRef = ref.getReference("users");
        userDataRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    if (Objects.equals(childSnap.child("email").getValue(), uEmail)){
                        phone.setText(Objects.requireNonNull(childSnap.child("phone").getValue()).toString());
                       userName.setText(Objects.requireNonNull(childSnap.child("userName").getValue()).toString());
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
        final EditText userName = findViewById(R.id.edit_userid);
        final EditText aemail = findViewById(R.id.edit_email);
        final EditText phone = findViewById(R.id.edit_phone);


        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.updateEmail(aemail.getText().toString());


        FirebaseDatabase ref = FirebaseDatabase.getInstance();
        DatabaseReference userDataRef = ref.getReference("users");
        userDataRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    if (Objects.equals(childSnap.child("email").getValue(), email)) {
                        childSnap.getRef().child("phone").setValue(phone.getText().toString());
                        childSnap.getRef().child("userName").setValue(userName.getText().toString());
                        childSnap.getRef().child("email").setValue(aemail.getText().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSettings(View view) {
        editUserInfo();
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(UserSettingsActivity.this, CareProviderHomeView.class);
        // Launch the browse emotions activity
        startActivity(intent);
    }

}
