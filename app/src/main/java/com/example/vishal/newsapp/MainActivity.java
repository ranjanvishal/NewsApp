package com.example.vishal.newsapp;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public  class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener, TabListener {
    public ViewPager viewPager;
    public TabAdapter tabAdapter;
    public android.app.ActionBar actionBar;
    public String[] tabNames = {"Recent", "National", "International"};

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] Navigation;
    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Navigation=getResources().getStringArray(R.array.Navigation);
        listView= (ListView) findViewById(R.id.drawer_list);
        listView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, Navigation));
        listView.setOnItemClickListener(this);



        viewPager = (ViewPager) findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0; i < 2; i++) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(this, Navigation[position]+"was selected",Toast.LENGTH_LONG).show();
    }



    }

