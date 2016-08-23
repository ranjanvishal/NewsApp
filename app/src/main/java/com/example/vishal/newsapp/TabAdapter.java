package com.example.vishal.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vishal on 05/08/16.
 */
public class TabAdapter extends SmartFragmentStatePagerAdapter {


    public TabAdapter(FragmentManager fm)
    {
        super(fm);
    }

       String fragments[]={"Recent","National","International"};

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return  Recent.newInstance(0,"Recent");
            case 1:
                return National.newInstance(1,"National");
            case 2:
                return  International.newInstance(2,"International");
            default:
                return null;


        }

    }


    @Override
    public float getPageWidth (int position) {
        return 1f;
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
