package com.example.vishal.newsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vishal on 05/08/16.
 */
public class TabAdapter extends FragmentPagerAdapter {


    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new Recent();
            case 1:
                return new National();



        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }
}
