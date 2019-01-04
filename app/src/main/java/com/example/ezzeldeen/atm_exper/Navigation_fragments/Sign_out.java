package com.example.ezzeldeen.atm_exper.Navigation_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ezzeldeen.atm_exper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sign_out extends Fragment {


    public Sign_out() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false);
    }

}
