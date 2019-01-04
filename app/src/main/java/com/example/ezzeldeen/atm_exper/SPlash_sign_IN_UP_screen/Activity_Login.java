package com.example.ezzeldeen.atm_exper.SPlash_sign_IN_UP_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezzeldeen.atm_exper.Navigation_fragments.NavigationDrawer;
import com.example.ezzeldeen.atm_exper.R;

public class Activity_Login extends AppCompatActivity {
    private static final String TAG = Activity_Login.class.getName();
    private Button btnRequest;

    private TextView txtView;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://samy123-001-site1.btempurl.com/api/login/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRequest = findViewById(R.id.button_signin);
        txtView = findViewById(R.id.login_credit_number);
        SharedPreferences sharedPref =getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("credit no", Integer.valueOf(txtView.getText().toString()));
        editor.commit();

        //Skip Button For Testing
        Button button =  findViewById(R.id.skip_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              sendAndRequestResponse();

                                          }
                                      }
        );
    }

    private void sendAndRequestResponse() {

        String urlB = url + "" + txtView.getText().toString().trim();
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String res = response;
                Log.e("respnse1", response);
                String replace = res.replace('"', ' ').trim().toLowerCase();
                Boolean boolean2 = Boolean.parseBoolean(replace);
                Log.e("here", String.valueOf(response));
                if (boolean2) {
                    Toast.makeText(Activity_Login.this, "valid", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Activity_Login.this, NavigationDrawer.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Activity_Login.this, "invalid", Toast.LENGTH_SHORT).show();
                    Log.v("credit", String.valueOf(boolean2));

                }
                Toast.makeText(getApplicationContext(), "Response :" + boolean2, Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error11 :" + error.toString());
                Log.e("response Errorhome", error + "");
                if (error instanceof NoConnectionError) {
                    Log.d("NoConnectionError>>>>>>>>", "NoConnectionError.......");
                    Toast.makeText(Activity_Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.d("AuthFailureError>>>>>>>>>", "AuthFailureError.......");
                    Toast.makeText(Activity_Login.this, "AuthFailure", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.d("ServerError>>>>>>>>>", "ServerError.......");
                    Toast.makeText(Activity_Login.this, "invalid Credit Number", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Log.d("NetworkError>>>>>>>>>", "NetworkError.......");
                    Toast.makeText(Activity_Login.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.d("ParseError>>>>>>>>>", "ParseError.......");
                    Toast.makeText(Activity_Login.this, "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.d("TimeoutError>>>>>>>>>", "TimeoutError.......");
                    Toast.makeText(Activity_Login.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
