package com.example.ezzeldeen.atm_exper.View_Pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.ezzeldeen.atm_exper.ViewPager_fragments.MapListView;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.MapView_Fragment;

import static com.example.ezzeldeen.atm_exper.View_Pager_adapter.TabFragment.int_items;





public class MyAdapter extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MapView_Fragment();
            case 1:
                return new MapListView();

        }
        return null;
    }

    @Override
    public int getCount() {


        return  int_items;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "           Map View            ";
            case 1:
                return "           List View           ";



        }

        return null;
    }
}
