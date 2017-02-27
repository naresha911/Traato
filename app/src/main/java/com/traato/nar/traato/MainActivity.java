package com.traato.nar.traato;

import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.traato.nar.traato.fragments.DrawerFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerFragment mDrawerFragment;
   // public DrawerFragment mDrawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prepare toolbar and navigation drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        } else {
            //Timber.e(new RuntimeException(), "GetSupportActionBar returned null");
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.content_description_open_navigation_drawer, R.string.content_description_close_navigation_drawer){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerFragment = (DrawerFragment)getFragmentManager().findFragmentById(R.id.main_navigation_drawer_fragment);

    }
}
