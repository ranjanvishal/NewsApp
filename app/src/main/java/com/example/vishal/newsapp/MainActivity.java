package com.example.vishal.newsapp;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public  class MainActivity extends FragmentActivity implements TabListener {
    public ViewPager viewPager;
    public TabAdapter tabAdapter;
    public android.app.ActionBar actionBar;
    public String [] tabNames ={"Recent","National","International"};

    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for(int i =0; i<3;i++)
        {
            actionBar.addTab(actionBar.newTab().setText(tabNames[i]).setTabListener(this));
         }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int postion) {
                actionBar.setSelectedNavigationItem(postion);
            }



            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }


            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }




    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
