package com.example.vishal.newsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vishal on 05/08/16.
 */
public class Recent extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setText("Recent News");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        tv.setBackgroundColor(Color.RED);
        tv.setTextAppearance(getActivity(),
                android.R.style.TextAppearance_Large);
        return tv;
    }
}


