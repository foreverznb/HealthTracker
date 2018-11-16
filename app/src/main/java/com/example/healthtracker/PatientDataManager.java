package com.example.healthtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Created by caochenlin on 2018/11/2.
 */

//TODO cite abram hindle student picker for this class

public class PatientDataManager {

    private Context context;
    final private static String patientKey = "Patient";

    PatientDataManager(Context context){
        this.context = context;
    }

    // save serialized string of patient object to shared preferences
    // calls method to convert patient object  to serialized string
    public void savePatientLocally(Patient patient) {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString(patientKey, patientToString(patient));
        editor.apply();
    }

    // load serialized string of patient from shared preferences
    // call method to convert serialized string back to Patient object
    public Patient loadPatientLocally()  {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String patientString = myPrefs.getString(patientKey, "");
        Patient patient = patientFromString(patientString);
        if(patient == null){
            return new Patient();
        }
        return patient;
    }

    // convert patient object to serialized string
    private String patientToString(Patient patient) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(patient);
            oo.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        byte[] bytes = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    // convert serialized string to Patient object
    private Patient patientFromString(String patientData) {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(patientData, Base64.DEFAULT));
        Patient patient = null;
        try {
            ObjectInputStream oi = new ObjectInputStream(bi);
            patient = (Patient)oi.readObject();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return patient;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void savePatientToDatabase(Patient patient){
        String userid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(userid).setValue(patient);
        reference.child(userid).child("isCaregiver").setValue("false");
    }

    public void elasticSearch(String keyword, String searchType){

    }

}
