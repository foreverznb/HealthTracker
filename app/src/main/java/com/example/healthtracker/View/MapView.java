package com.example.healthtracker.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthtracker.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/* Code for testing the functionality of google maps within our program reused from a
3-part tutorial from 
*https://www.viralandroid.com/2016/04/google-maps-android-api-getting-started-tutorial.html, Apr 24, 2016, viewed: Oct 23, 2018* & 
*https://www.viralandroid.com/2016/04/get-current-location-google-maps-android-api-tutorial.html, posted: Apr 24, 2016, viewed: Oct 23, 2018* & 
*https://www.viralandroid.com/2016/04/google-maps-android-api-adding-search-bar-part-3.html, 
posted: Apr 25, 2016, viewed: Oct 23, 2018*
*/


/*
 * MapView will allow Patients to view all of the geolocations associated with their problems in
 * a map. Careproviders can view all of the geolocations associated with their patients'
 * problems.
 */
public class MapView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*
     * Search for a location by address.
     */
    public void onMapSearch(View view) {
        EditText locationSearch = findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            try {
                Geocoder geocoder = new Geocoder(this);
                addressList = geocoder.getFromLocationName(location, 1);
                //Log.d("array",addressList.toString());
                if  (!addressList.isEmpty()){
                Address address = addressList.get(0);
                String Locality = address.getLocality();
                Toast.makeText(getApplicationContext(),Locality,Toast.LENGTH_SHORT).show();
                //LatLng LatLng = new LatLng(address.getLatitude(), address.getLongitude());
                goToLocation(address.getLatitude(), address.getLongitude(),15);}
                else{
                    String no_address = "No address found";
                    Toast.makeText(getApplicationContext(),no_address,Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    /*
     * Override the onMapReady method in order to set what will apear when the map is initiated.
     */
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(27.700769, 85.300140);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marke"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);*/

        goToLocation(27.700769, 85.300140, 15);
    }

    public void goToLocation(double latitude, double longitude, int zoom ){
        LatLng LatLng = new LatLng(latitude, longitude );
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LatLng,15);
        mMap.addMarker(new MarkerOptions().position(LatLng).title("Marker"));
        mMap.moveCamera(update);


    }
}
