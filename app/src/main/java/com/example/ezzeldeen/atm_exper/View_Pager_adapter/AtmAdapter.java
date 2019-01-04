package com.example.ezzeldeen.atm_exper.View_Pager_adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ezzeldeen.atm_exper.R;
import com.example.ezzeldeen.atm_exper.ViewPager_fragments.ATM;

import java.util.ArrayList;

public class AtmAdapter extends ArrayAdapter<ATM> {


    private static final String LOG_TAG = AtmAdapter.class.getSimpleName();


    public AtmAdapter(Activity context, ArrayList<ATM> atms) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, atms);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate( R.layout.list_item_atm, parent, false);

        }


        ATM currentATm = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.atm_name);
        nameTextView.setText(currentATm.getName());
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.atm_address);

        numberTextView.setText(currentATm.getAddress());
        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.distance);
        distanceTextView.setText(currentATm.getDistance()+"");

        TextView balanceTextView = (TextView) listItemView.findViewById(R.id.atm_balance);
        balanceTextView.setText(currentATm.getbalance()+" ");

        TextView transactionTextView = (TextView) listItemView.findViewById(R.id.atm_transaction);
        transactionTextView.setText(currentATm.getT_N_last_hour()+" ");
        return listItemView;
    }
}
