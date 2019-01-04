package com.example.ezzeldeen.atm_exper.ViewPager_fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.Loader;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezzeldeen.atm_exper.Get_Atm_Data.AtmLoader;
import com.example.ezzeldeen.atm_exper.MapsActivity;
import com.example.ezzeldeen.atm_exper.Nearby_Places.GetNearbyATMsData;
import com.example.ezzeldeen.atm_exper.R;
import com.example.ezzeldeen.atm_exper.View_Pager_adapter.AtmAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapListView extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<ATM>> {
   public ArrayList<ATM> atms=new ArrayList<ATM>();
    private static final int ATMS_LOADER_ID = 1;
    private List<ATM> aaa = new ArrayList<ATM>();
    ListView ListAtm;
    MapView_Fragment mapViewFragment;
    double myLat;
    double myLang;
    TextView empty;
    ArrayList<ATM> atmcolor ;
    GoogleMap googleMap;
    EditText Withdrow ;

    private static final String ATMS_REQUEST_URL = "http://samy123-001-site1.btempurl.com/api/atm";




    public MapListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_list_view, container, false);
         empty = view.findViewById(R.id.empty_view);
        FloatingActionButton button = view.findViewById(R.id.button);
         ListAtm = view.findViewById(R.id.list);
        Withdrow =view.findViewById(R.id.withdrow);


         mapViewFragment = new MapView_Fragment();
         myLat = C_location.latitude;
         myLang = C_location.longitude;
        setHasOptionsMenu(true);



        atms = GetNearbyATMsData.getAtms();

        for (int i =0 ; i<atms.size();i++){
            atms.get(i).setbalance(0.0);
            atms.get(i).setT_N_last_hour(0);
            atms.get(i).setPrecentagofdistance(0.0);
            atms.get(i).setPrecentagofbalance(0.0);
            atms.get(i).setPrecentagofcrowding(0.0);
        }


        //first order by balance
        orderdByDistance();







        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  FragmentTransaction ft = getFragmentManager().beginTransaction();
               // ft.detach(MapListView.this).attach(MapListView.this).commit();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(MapListView.this).attach(MapListView.this).commit();

            }
        });

            android.support.v4.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(ATMS_LOADER_ID, null,  MapListView.this);





         ListAtm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(), MapsActivity.class);

                intent.putExtra("ATM_NAME", atms.get(i).getName());
                intent.putExtra("ATM_ADDRESS", atms.get((i)).getAddress());
                intent.putExtra("LATITUDE", atms.get((i)).getLat());
                intent.putExtra("LONGITUDE", atms.get((i)).getLng());





                startActivity(intent);

            }
        });
        // Inflate the layout for this fragment
        return view;


    }


    @Override
    public Loader<List<ATM>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(ATMS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", "31348b4c-ec34-4a51-98d4-dfc8471f2a6c");
        return new AtmLoader(getContext(), uriBuilder.toString());
    }

    @SuppressLint("RestrictedApi")
    @Override



    public void onLoadFinished(Loader<List<ATM>> loader, List<ATM> dataList) {
        Toast.makeText(getApplicationContext(), " refreshing ", Toast.LENGTH_SHORT).show();


        if (atms.size() > 0) {

            for (int i = 0; i < atms.size(); i++) {

                for (int k = 0; k < atms.size(); k++) {
                    String Atm_name1 = atms.get(i).getName().substring(0,3)+atms.get(i).getName().substring(atms.get(i).getName().length()-3,atms.get(i).getName().length()); //atms.get(i).getName().length()
                    String Atm_name2 = dataList.get(k).getName().substring(0,3)+dataList.get(k).getName().substring(dataList.get(k).getName().length()-3,dataList.get(k).getName().length());//dataList.get(k).getName().length()

                    if (Atm_name1.replace(" ", "").equalsIgnoreCase(Atm_name2.replace(" ", ""))){
                        atms.get(i).setbalance(dataList.get(k).getbalance());
                        atms.get(i).setT_N_last_hour(dataList.get(k).getT_N_last_hour());
                      //  Log.e("++++++++++ " + atms.get(i).getName() + " balance: " + atms.get(i).getbalance(), "+++++++++++++++++++++++++++");


                    }


                }
               // Log.e("atms mohamed (" + atms.get(i).getName() + ")", "atms taha (" + dataList.get(i).getName() + ")");
            }
        }


//        for (int i=0;i < dataList.size();i++)
//        {
//
//
//            Log.e("Value of element balance "+i, String.valueOf(dataList.get(i).getbalance()));
//            Log.e("Value of element transaction"+i, String.valueOf(dataList.get(i).getT_N_last_hour()));
//            Log.e("Value of element transaction"+i, String.valueOf(dataList.get(i).getT_N_last_hour()));
//        }

//        if (dataList != null && !dataList.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "list is not empty", Toast.LENGTH_SHORT).show();
//        }


    }
    @Override
    public void onLoaderReset(Loader<List<ATM>> loader) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_order, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.order_by_balance:
                orderByBalance();

                break;
            case R.id.order_by_crowding:
                orderByCrowding();
                break;
            case R.id.order_by_distance:
                orderdByDistance();

                break;
            case R.id.order_by_suitable:
                suitableAtm();

                break;


        }

        return super.onOptionsItemSelected(item);
    }
    public void orderdByDistance(){




        for (int i = 0; i < atms.size(); i++) {

            for (int k = i + 1; k < atms.size(); k++) {
                float results1[] = new float[10];
                Location.distanceBetween(myLat, myLang, atms.get(i).getLat(), atms.get(i).getLng(), results1);
                float results2[] = new float[10];
                Location.distanceBetween(myLat, myLang, atms.get(k).getLat(), atms.get(k).getLng(), results2);
                if (results1[0] > results2[0]) {
                    Collections.swap(atms, i, k);

                }
                Log.e("result1 after : " + results1[0], "result2 after : " + results2[0]);

            }

          //  Log.e("balance balance  :=== " + atms.get(i).getName(), "balance balance================================= ");
        }

        AtmAdapter adapter = new AtmAdapter(getActivity(), atms);
        ListAtm.setAdapter(adapter);
        ListAtm.setEmptyView(empty);

    }
    public void orderByBalance(){
        //value that user want to withdraw
        ArrayList<ATM> Values = new ArrayList<ATM>();
        for(int w = 0 ;w <atms.size();w++){
            if(atms.get(w).getbalance()>checkValueAvailableInAtm()+10000){
                Values.add(atms.get(w));
            }
        }

        for (int i = 0; i < Values.size(); i++) {

            for (int k = i + 1; k < Values.size(); k++) {
                double x = Values.get(i).getbalance();
                double y = Values.get(k).getbalance();

                if (x <= y) {
                    Collections.swap(Values, i, k);
                }

            }
        }

        AtmAdapter adapter = new AtmAdapter(getActivity(), Values);
        ListAtm.setAdapter(adapter);
        ListAtm.setEmptyView(empty);

        MapView_Fragment mMapViewFragment = new MapView_Fragment();
        C_location.map.clear();

        for(int i = 0 ; i<Values.size()/2;i++){

            LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLngg);
            markerOptions.title(Values.get(i).name);

            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            C_location.map.addMarker(markerOptions);
        }

        for(int i = Values.size()/2 ; i<Values.size();i++){

            LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLngg);
            markerOptions.title(Values.get(i).name);

            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            C_location.map.addMarker(markerOptions);


        }



    }
        public void orderByCrowding(){
            ArrayList<ATM> Values = new ArrayList<ATM>();
            for(int w = 0 ;w <atms.size();w++){
                if(atms.get(w).getbalance()>checkValueAvailableInAtm()+10000){
                   Values.add(atms.get(w));
                }
            }

            for (int i = 0; i < Values.size(); i++) {

                for (int k = i + 1; k < Values.size(); k++) {
                    int x = Values.get(i).getT_N_last_hour();
                    int y = Values.get(k).getT_N_last_hour();

                    if (x >= y) {
                        Collections.swap(Values, i, k);
                    }

                }
            }

            AtmAdapter adapter = new AtmAdapter(getActivity(), Values);
            ListAtm.setAdapter(adapter);
            ListAtm.setEmptyView(empty);
            //

            MapView_Fragment mMapViewFragment = new MapView_Fragment();
            C_location.map.clear();

            for(int i = 0 ; i<Values.size()/2;i++){

                LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLngg);
                markerOptions.title(Values.get(i).name);

                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                C_location.map.addMarker(markerOptions);
            }

            for(int i = Values.size()/2 ; i<Values.size();i++){

                LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLngg);
                markerOptions.title(Values.get(i).name);

                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                C_location.map.addMarker(markerOptions);


            }




        }
        public void suitableAtm(){
        //value that user want to withdraw
            ArrayList<ATM> Values = new ArrayList<ATM>();
            for(int w = 0 ;w <atms.size();w++){
                if(atms.get(w).getbalance()>checkValueAvailableInAtm()+10000){
                    Values.add(atms.get(w));
                }
            }

        double maxdistance = 0.0;
        double maxbalance = 0.0;
        int maxtransactionbyhour = 0;
        for(int i =0;i<Values.size();i++){
            if(Values.get(i).getDistance()>maxdistance)
                maxdistance = Values.get(i).getDistance();

            if(Values.get(i).getbalance()>maxbalance)
                maxbalance = Values.get(i).getbalance();

            if(Values.get(i).getT_N_last_hour()>maxtransactionbyhour)
                maxtransactionbyhour = Values.get(i).getT_N_last_hour();


        }
        for (int i =0;i<Values.size();i++){
            Values.get(i).setPrecentagofdistance(20-((Values.get(i).getDistance()/(maxdistance+7))*20));
            Values.get(i).setPrecentagofbalance((Values.get(i).getbalance()/(maxbalance))*100);
            Values.get(i).setPrecentagofcrowding(120-((Values.get(i).getT_N_last_hour()/(maxtransactionbyhour+15))*120));
            if(Values.get(i).getbalance()==0.0){
                Values.get(i).setPrecentagofcrowding(0.0);
            }
        }
            for (int i = 0; i < Values.size(); i++) {

                for (int k = i + 1; k < Values.size(); k++) {
                    double x = (Values.get(i).getPrecentagofbalance()+Values.get(i).getPrecentagofcrowding()+Values.get(i).getPrecentagofdistance());
                    double y = (Values.get(k).getPrecentagofbalance()+Values.get(k).getPrecentagofcrowding()+Values.get(k).getPrecentagofdistance());


                    if (x <= y) {
                        Collections.swap(Values, i, k);
                    }

                }
            }

            AtmAdapter adapter = new AtmAdapter(getActivity(), Values);
            ListAtm.setAdapter(adapter);
            ListAtm.setEmptyView(empty);
            //
            MapView_Fragment mMapViewFragment = new MapView_Fragment();
            C_location.map.clear();

            for(int i = 0 ; i<Values.size()/2;i++){

                LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
                MarkerOptions markerOptions = new MarkerOptions();
               markerOptions.position(latLngg);
                markerOptions.title(Values.get(i).name);

               markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
              C_location.map.addMarker(markerOptions);
            }

            for(int i = Values.size()/2 ; i<Values.size();i++){

                LatLng latLngg = new LatLng(Values.get(i).getLat(), Values.get(i).getLng());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLngg);
                markerOptions.title(Values.get(i).name);

                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                C_location.map.addMarker(markerOptions);


            }


        }



    double checkValueAvailableInAtm(){

        double x;
        if(!Withdrow.getText().toString().equalsIgnoreCase("")){
            x =Double.valueOf(Withdrow.getText().toString());
        }
        else{x=0.0;}

        return x;

    }
}

