package edu.team27.perfectcube.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.team27.perfectcube.R;

/**
 * Created by suinyun on 4/1/18.
 */

public class MapViewActivity extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public MapViewActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_mapview, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //where you add the pins
    }

}
