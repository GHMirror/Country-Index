package com.zybooks.countryindex;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Countries countryInfo;

    private Boolean fromMain = true;

    public MainActivity activity;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                countryInfo = null;
            } else {
                fromMain = false;
                String countryJson = extras.getString("country");
                Gson gson = new Gson();
                countryInfo = gson.fromJson(countryJson, Countries.class);
            }
        } else {
            String countryJson = (String) savedInstanceState.getSerializable("country");
            Gson gson = new Gson();
            countryInfo = gson.fromJson(countryJson, Countries.class);
        }
    }

    public void backToMain(View view) {
        if(fromMain) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(context, CountryOverview.class);
            Bundle extras = getIntent().getExtras();
            String country= extras.getString("countryName");
            String isFavStr = extras.getString("isFavorite");
            String countryJson = extras.getString("country");
            intent.putExtra("country", countryJson);
            intent.putExtra("countryName", country);
            intent.putExtra("isFavorite", isFavStr);
            startActivity(intent);
        }
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
        mMap = googleMap;

        if (countryInfo != null) {
            LatLng marker = new LatLng(Float.parseFloat(countryInfo.getLatitude()), Float.parseFloat(countryInfo.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(marker).title(countryInfo.getCountryName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        }

    }
}
