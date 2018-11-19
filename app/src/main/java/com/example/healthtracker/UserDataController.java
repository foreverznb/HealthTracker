package com.example.healthtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by caochenlin on 2018/11/2.
 */

//todo cite student picker

public class UserDataController<E> {


    final private static String userKey = "Key";
    private Context context;

    UserDataController(Context context) {
        this.context = context;
    }

    // save serialized string of user object to shared preferences
    // calls method to convert user object  to serialized string

    public static CareProvider loadCareProviderData(Context context) {
        if (ElasticsearchController.testConnection(context)) {
            // Download user data with elastic search
            SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            String userID = myPrefs.getString("userID", "");
            ElasticsearchController.GetCareProvider getCareProvider = new ElasticsearchController.GetCareProvider();
            getCareProvider.execute(userID);
            CareProvider careProvider = null;
            try {
                careProvider = getCareProvider.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return careProvider;
        } else {
            // Load local cache of user data
            return new UserDataController<CareProvider>(context).loadUserLocally();
        }
    }

    public static Patient loadPatientData(Context context) {
        if (ElasticsearchController.testConnection(context)) {
            // check if local cache and server in sync
            //if not sync by uploading local cache

            // Download user data with elastic search
            SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            String userID = myPrefs.getString("userID", "");
            ElasticsearchController.GetPatient getPatient = new ElasticsearchController.GetPatient();
            getPatient.execute(userID);
            Patient patient = null;
            try {
                patient = getPatient.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return patient;
        } else {
            // Load local cache of user data
            return new UserDataController<Patient>(context).loadUserLocally();
        }
    }

    public static Patient loadMyPatientById(Context context, String ID) {
        if (ElasticsearchController.testConnection(context)) {

            // Download user data with elastic search
            ElasticsearchController.GetPatient getPatient = new ElasticsearchController.GetPatient();
            getPatient.execute(ID);
            Patient patient = null;
            try {
                patient = getPatient.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return patient;
        } else {
            // Load local cache of user data
            return new UserDataController<Patient>(context).loadUserLocally();
        }
    }

    public static void savePatientData(Context context, Patient patient) {
        // save online if possible
        if (ElasticsearchController.testConnection(context)) {
            Toast.makeText(context, "Saved changes", Toast.LENGTH_LONG).show();
            ElasticsearchController.AddPatient addPatientTask = new ElasticsearchController.AddPatient();
            addPatientTask.execute(patient);
        } else {
            Toast.makeText(context, "Could not reach server. Changes saved locally.", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Sync data when a connection is available to save changes to server.", Toast.LENGTH_LONG).show();

        }
        // save to local cache
        new UserDataController<Patient>(context).saveUserLocally(patient);
    }

    public static void saveCareProviderData(Context context, CareProvider careProvider) {
        // save online if possible
        if (ElasticsearchController.testConnection(context)) {
            Toast.makeText(context, "Saved changes", Toast.LENGTH_LONG).show();
            ElasticsearchController.AddCareProvider addCareProviderTask = new ElasticsearchController.AddCareProvider();
            addCareProviderTask.execute(careProvider);
        } else{
            Toast.makeText(context, "Could not reach server. Changes saved locally.", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Sync data when a connection is available to save changes to server.", Toast.LENGTH_LONG).show();
        }
        // save to local cache
        new UserDataController<CareProvider>(context).saveUserLocally(careProvider);
    }

    /**
     * @param user
     */
    private void saveUserLocally(E user) {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString(userKey, objectToString(user));
        editor.apply();
    }

    // load serialized string of user from shared preferences
    // call method to convert serialized string back to user object
    private E loadUserLocally() {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String userString = myPrefs.getString(userKey, "");
        E user = objectFromString(userString);
        if (user == null) {
            return null;
        }
        return user;
    }

    // convert object to serialized string
    private String objectToString(E data) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(data);
            oo.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        byte[] bytes = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    // convert serialized string to object
    private E objectFromString(String data) {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(data, Base64.DEFAULT));
        E user = null;
        try {
            ObjectInputStream oi = new ObjectInputStream(bi);
            user = (E) oi.readObject();
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return user;
    }

    public static void syncPatientData(Context context) {
        if (ElasticsearchController.testConnection(context)) {
            // upload cached user data
            Patient user = new UserDataController<Patient>(context).loadUserLocally();
            UserDataController.savePatientData(context, user);
        } else {
            Toast.makeText(context, "No internet connection available. Unable to sync.", Toast.LENGTH_LONG).show();
        }
    }

    public static void syncCareProviderData(Context context) {
        if (ElasticsearchController.testConnection(context)) {
            // upload cached user data
            CareProvider user = new UserDataController<CareProvider>(context).loadUserLocally();
            UserDataController.saveCareProviderData(context, user);
        } else {
            Toast.makeText(context, "No internet connection available. Unable to sync.", Toast.LENGTH_LONG).show();
        }
    }

    public static String serializeRecord(Context context, PatientRecord record){
      return new UserDataController<PatientRecord>(context).objectToString(record);
    }

    public static PatientRecord unSerializeRecord(Context context, String recordString){
        return new UserDataController<PatientRecord>(context).objectFromString(recordString);
    }


}
