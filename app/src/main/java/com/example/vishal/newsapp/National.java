package com.example.vishal.newsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vishal on 05/08/16.
 */
public class National extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("National News");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        tv.setBackgroundColor(Color.YELLOW);
        tv.setTextAppearance(getActivity(),
                android.R.style.TextAppearance_Large);
        return tv;
    }


}
