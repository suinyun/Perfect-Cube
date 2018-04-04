package edu.team27.perfectcube.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterInfo;

/**
 * Created by suinyun on 4/1/18.
 */

public class MapViewActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public MapViewActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_mapview, container, false);
    }*/ //I commented this, because onMapReadyCallback doesn't implement this, and causes problems with build

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        ArrayList<ShelterInfo> shelters = new ArrayList<>(Arrays.asList(WelcomeActivity.getSdb()
                .shelterDao().loadAllShelters()));
        for (ShelterInfo shelter : shelters) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(shelter.getLatitude(), shelter.getLongitude()))
                    .title(shelter.getShelterName()));
        }
    }

}
