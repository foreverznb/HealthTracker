package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

// extends
public class LoginActivity extends AppCompatActivity {

    private EditText UserID, Password;
    private CheckBox checkBox;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set screen to layout specified in activity_main
        setContentView(R.layout.activity_login);
        //mRegister = (TextView) findViewById(R.id.link_register);
        UserID = findViewById(R.id.userID);
        Password = findViewById(R.id.login_password);
        checkBox = findViewById(R.id.CareGiverLogin);
        context = this;
    }


    public void UserLogin(View view) throws ExecutionException, InterruptedException {
        if (ElasticsearchController.testConnection(context)) {
            String userID = UserID.getText().toString();
            SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putString("userID", userID);
            editor.apply();
            if (!isEmpty(UserID.getText().toString()) && !isEmpty(Password.getText().toString())) {
                if (checkBox.isChecked()) {
                    CareProvider careProvider;
                    ElasticsearchController.GetCareProvider getCareProvider = new ElasticsearchController.GetCareProvider();
                    getCareProvider.execute(userID);
                    careProvider = getCareProvider.get();
                    if (careProvider != null) {
                        Intent intent = new Intent(LoginActivity.this, CareProviderHomeView.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Unknown Account", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Patient patient;
                    ElasticsearchController.GetPatient getPatient = new ElasticsearchController.GetPatient();
                    getPatient.execute(userID);
                    patient = getPatient.get();
                    if (patient != null) {
                        Intent intent = new Intent(LoginActivity.this, PatientHomeView.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Account could not be reached.", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(LoginActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isEmpty(String string){
        return string.equals("");
    }

    // Method containing the new intent which will bring user to the browse emotions activity and layout screen
    public void CreateAccount(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);// Launch the browse emotions activity
        startActivity(intent);
    }

}