package com.example.ezzeldeen.atm_exper.Nearby_Places;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ezzeldeen.atm_exper.ViewPager_fragments.ATM;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.C_location;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.MapView_Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.MapView_Fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetNearbyATMsData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;
    public static ArrayList<ATM> Atms = new ArrayList<ATM>();

    @Override
    protected String doInBackground(Object... params) {
        try {

            Log.d("GetNearbyATMsData", "doInBackground entered");
            mMap = (GoogleMap) params[0];
            url = (String) params[1];

            UrlConnection urlConnection = new UrlConnection();
            googlePlacesData = urlConnection.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GooglePlacesReadTask", "onPostExecute Entered");
        List<HashMap<String, String>> nearbyPlacesList = null;
        DataParser dataParser = new DataParser();
        nearbyPlacesList =  dataParser.parse(result);
        ShowNearbyPlaces(nearbyPlacesList);
        Log.d("GooglePlacesReadTask", "onPostExecute Exit");
    }

    private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
        Atms.clear();
        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            Log.d("onPostExecute","Entered into showing locations");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            float results[]=new float[10];
            Location.distanceBetween(C_location.latitude,C_location.longitude,lat,lng,results);

            markerOptions.title(placeName + " : " + vicinity);
            Atms.add(new ATM(placeName,vicinity,Math.abs(lng),Math.abs(lat),Math.floor((results[0]/1000) * 100) / 100  ));
            // Log.e("Atms",placeName + " " + vicinity +"lat : " + lat+"lng : " + lng);
            mMap.addMarker(markerOptions);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        }
    }
    public static ArrayList<ATM> getAtms(){

        return Atms;

    }
}
