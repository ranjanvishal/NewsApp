package com.example.vishal.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vishal on 05/08/16.
 */
public class TabAdapter extends FragmentPagerAdapter {


    public TabAdapter(FragmentManager fm)
    {
        super(fm);
    }

       String fragments[]={"Recent","National","International"};

    @Override
    public Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new Recent();
            case 1:
                return new National();
            case 2:
                return  new International();
            default:
                return null;


        }

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
