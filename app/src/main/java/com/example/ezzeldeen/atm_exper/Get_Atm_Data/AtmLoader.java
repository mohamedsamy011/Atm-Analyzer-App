package com.example.ezzeldeen.atm_exper.Get_Atm_Data;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.ezzeldeen.atm_exper.ViewPager_fragments.ATM;

import java.util.List;

/**
 * Created by emad on 3/17/2018.
 */

public class AtmLoader extends AsyncTaskLoader<List<ATM>> {
    private String mUrl;
    private static final String LOG_TAG = AtmLoader.class.getName();

    public AtmLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG, "calling : onStartLoading ");
    }

    @Override
    public List<ATM> loadInBackground() {
        Log.i(LOG_TAG, "calling : loadInBackground ");
        if (mUrl.length() < 1 || mUrl == null) {
            return null;
        }
        List<ATM> Atm = QueryUtils.fetchAtmData(mUrl);
        return Atm;
    }
}

