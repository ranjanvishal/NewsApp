package com.example.vishal.newsapp;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public  class MainActivity extends FragmentActivity {
    public ViewPager viewPager;
    public TabAdapter tabAdapter;
    //public android.app.ActionBar actionBar;
    public String[] tabNames = {"Recent", "National", "International"};
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Navigation=getResources().getStringArray(R.array.Navigation);
        listView= (ListView) findViewById(R.id.drawer_list);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Navigation));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: Error");


            }
        });



        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);
        tabAdapter = new TabAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabAdapter);
        viewPager.getCurrentItem();
        viewPager.setCurrentItem(3);
       // actionBar = getActionBar();
        //assert actionBar != null;
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //for (int i = 0; i < 3; i++) {

        //    actionBar.addTab(actionBar.newTab().setText(tabNames[i]).setTabListener(this));

        //}


        //tabAdapter.getRegisteredFragment(0);
        tabAdapter.getRegisteredFragment(viewPager.getCurrentItem());
        viewPager.setOffscreenPageLimit(3);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

               // actionBar.setSelectedNavigationItem(position);
            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }



 /*   @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {




    }*/


}


