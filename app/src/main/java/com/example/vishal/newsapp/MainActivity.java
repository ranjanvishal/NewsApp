package com.example.vishal.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.vishal.newsapp.R.drawable.*;
import static com.example.vishal.newsapp.R.string.drawer_close;

public  class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    public ViewPager viewPager;
    public TabAdapter tabAdapter;
    //public android.app.ActionBar actionBar;
    public String[] tabNames = {"Recent", "National", "International"};
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] Navigation;
    private ActionBarDrawerToggle drawerListner;
    final String[] fragments= {
            "com.example.vishal.newsapp.Recent",
            "com.example.vishal.newsapp.National",
            "com.example.vishal.newsapp.International"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListner=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.pic2,R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this,"Drawer Opened",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this,"Drawer Closed",Toast.LENGTH_SHORT).show();
            }
        };

        drawerLayout.setDrawerListener(drawerListner);


        Navigation = getResources().getStringArray(R.array.Navigation);
        listView = (ListView) findViewById(R.id.drawer_list);
        ArrayAdapter adapter = new ArrayAdapter(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, Navigation);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                                                drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                                    @Override
                                                    public void onDrawerClosed(View drawerView) {
                                                        super.onDrawerClosed(drawerView);
                                                        viewPager.getCurrentItem();
                                                        viewPager.setCurrentItem(position);
                                                    }

                                                });
                                                drawerLayout.closeDrawer(listView);


                                            }
                                        });





        viewPager = (ViewPager) findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        // actionBar = getActionBar();
        //assert actionBar != null;
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //for (int i = 0; i < 3; i++) {

        //    actionBar.addTab(actionBar.newTab().setText(tabNames[i]).setTabListener(this));

        //}


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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,Navigation[position]+" was selected",Toast.LENGTH_LONG).show();
        selectItem(position);
    }

    public void selectItem(int position){
        listView.setItemChecked(position,true);
        setTitle(Navigation[position]);
    }
    //public void setTitle(String title, Activity getSupportActionBar){

       // getSupportActionBar.setTitle(title);
    //}






 /*   @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
    }
    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
    }*/


}
