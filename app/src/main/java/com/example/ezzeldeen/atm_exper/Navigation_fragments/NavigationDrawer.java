package com.example.ezzeldeen.atm_exper.Navigation_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ezzeldeen.atm_exper.FAQ_Fragment;
import com.example.ezzeldeen.atm_exper.R;
import com.example.ezzeldeen.atm_exper.SPlash_sign_IN_UP_screen.Activity_Login;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.ATM;
import com.example.ezzeldeen.atm_exper.View_Pager_adapter.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ATMS_LOADER_ID = 1;
    private List<ATM> aaa = new ArrayList<ATM>();

    private static final String ATMS_REQUEST_URL = "http://tahasaleh24-001-site1.gtempurl.com/api/atm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        if (checckInrenet()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containermap, new TabFragment()).commit();
        } else if (!checckInrenet()) {
            TextView text = findViewById(R.id.no_internet);
            text.setText("No Internet Connection");
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.navigation_drawer, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.suit_ATM) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containermap, new TabFragment()).addToBackStack(null).commit();

            // Handle the camera action
        } else if (id == R.id.ner_atm) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containermap, new TabFragment()).addToBackStack(null).commit();


        } else if (id == R.id.Home && checckInrenet()) {

            getSupportFragmentManager().beginTransaction().replace(R.id.containermap, new TabFragment()).addToBackStack(null).commit();


        }
        else if(id==R.id.sinout){

            Intent intent=new Intent(NavigationDrawer.this,Activity_Login.class);
            startActivity(intent);

        }

        else if (id==R.id.FAQ){

            getSupportFragmentManager().beginTransaction().replace(R.id.containermap,new FAQ_Fragment()).addToBackStack(null).commit();

        }
        else if (id == R.id.about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containermap, new About()).addToBackStack(null).commit();

        } else if (id == R.id.sinout) {
            Intent intent = new Intent(NavigationDrawer.this, Activity_Login.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checckInrenet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else
            return false;
    }


}
