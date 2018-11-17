package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

// extends
public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";

    private EditText UserID, Password;
    private CheckBox checkBox;
    private Context context;


    /**
     * onCreate launched on LoginActivity creation
     *
     * @param savedInstanceState SIS
     */
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


    /**
     * UserLogin() is initiated by the user clicking on the login button. If the user leaves a field empty when trying to log in
     * they will be shown a toast message indicating a field has been left empty. If the user tries to login with an unregistered
     * account they will be shown a toast message telling them that this account is not valid.
     * <p>
     * Upon correct login credentials the user will be brought to the appropriate login screen for patient or care provider
     * based on if the checkbox indicating which type of user is attempting to login is checked. The app checks elastic search
     * database for the entered username to see if it has been entered. Additionally when then this method is called it calls
     * test connection to see if their is an internet connection.
     *
     * @param view the view for the login layout included for onClick methods in XML
     * @throws ExecutionException   catch exception where ...
     * @throws InterruptedException catch exception where ...
     */
    public void UserLogin(View view) throws ExecutionException, InterruptedException {
        if (!testConnection()) {
            String userID = UserID.getText().toString();
            if (isEmpty(UserID.getText().toString()) && isEmpty(Password.getText().toString())) {
                if (checkBox.isChecked()) {
                    CareProvider careProvider;
                    ElasticUserController.GetCareProvider getCareProvider = new ElasticUserController.GetCareProvider();
                    getCareProvider.execute(userID);
                    careProvider = getCareProvider.get();
                    if (careProvider != null) {
                        Intent intent = new Intent(LoginActivity.this, CareProviderHomeView.class);
                        // Launch the browse emotions activity
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Unknown Account", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Patient patient;
                    ElasticUserController.GetPatient getPatient = new ElasticUserController.GetPatient();
                    getPatient.execute(userID);
                    patient = getPatient.get();
                    if (patient != null) {
                        Intent intent = new Intent(LoginActivity.this, PatientHomeView.class);
                        // Launch the browse emotions activity
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Account could not be reached", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(LoginActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * isEmpty() tests if one of the login fields is not filled in
     *
     * @param string string provided on method call to be tested if it is blank
     * @return returns a boolean object on whether the provided string is blank
     */
    private boolean isEmpty(String string) {
        return !string.equals("");
    }

    /**
     * Brings the user to the create account screen upon clicking the create
     *
     * @param view the view of the login layout included for onClick methods in XML
     */
    public void CreateAccount(View view) {
        // Create an intent object containing the bridge to between the two activities
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);// Launch the browse emotions activity
        startActivity(intent);
    }

    /**
     * testConnection() checks for online connectivity on either wifi or mobile data and returns the connectivity state
     *
     * @return returns a boolean object on whether the user is connected to wifi or cellular data for online connectivity checks
     */
    public boolean testConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //check for network connection
        assert connectivityManager != null;
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.CONNECTED &&
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.CONNECTED;
    }


}