package edu.team27.perfectCube.controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

import edu.team27.perfectCube.R;
import edu.team27.perfectCube.model.ShelterDatabase;
import edu.team27.perfectCube.model.ShelterInfo;

/**
 * does map stuff
 *
 * */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int m = R.id.map;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(m);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ShelterDatabase sdb = WelcomeActivity.getSdb();
        Iterable<ShelterInfo> shelters = new ArrayList<>(
                Arrays.asList(sdb.shelterDao().loadAllShelters()));
        for (ShelterInfo shelter : shelters) {
            LatLng ll = new LatLng(shelter.getLatitude(), shelter.getLongitude());
            MarkerOptions mo = new MarkerOptions();
            mo = mo.position(ll);
            mo = mo.title(shelter.getShelterName());
            googleMap.addMarker(mo).setSnippet("Phone Number: " + shelter.getPhoneNumber());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
        }
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


    }
}
