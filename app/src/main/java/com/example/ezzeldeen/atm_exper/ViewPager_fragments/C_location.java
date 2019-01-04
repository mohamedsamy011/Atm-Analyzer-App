package com.example.ezzeldeen.atm_exper.ViewPager_fragments;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by emad on 3/24/2018.
 */

public class C_location {

    public static double latitude ;
    public static double longitude;
    public static GoogleMap map ;

    public C_location() {
    }

    public C_location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public C_location(GoogleMap map){
        this.map=map;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setMap(GoogleMap map) {
        this.map=map;
    }
}
