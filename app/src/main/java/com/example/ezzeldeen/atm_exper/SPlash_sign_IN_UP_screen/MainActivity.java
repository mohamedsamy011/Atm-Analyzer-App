package com.example.ezzeldeen.atm_exper.SPlash_sign_IN_UP_screen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ezzeldeen.atm_exper.Navigation_fragments.NavigationDrawer;
import com.example.ezzeldeen.atm_exper.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.common.util.UriUtil;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize Fresco & Declare Permissions
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);


        //intialize layout for image
        SimpleDraweeView myDraweeView
                = (SimpleDraweeView)findViewById(R.id.my_image_view);

        //creating path for image
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                .path(String.valueOf(R.drawable.atm))
                .build();
// uri looks like res:/123456789
        myDraweeView.setImageURI(uri);

        DraweeController controller =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                        .build();

        myDraweeView.setController(controller);

        getSupportActionBar().hide();

//move to another activity after some milliseconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(MainActivity.this, Activity_Login.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 3305);


    }


}
