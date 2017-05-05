package com.powernusa.andy.mycheese;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidget();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        if(navView != null){
            setupDrawerContent(navView);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * *********************************************************************************************
     *
     * Initialization
     *
     *
     * *********************************************************************************************
     *
     */

    private DrawerLayout mDrawerLayout;

    private void initializeWidget(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    private void setupDrawerContent(NavigationView navView){
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(),"Home clicked",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        //item.setChecked(true);
                        return true;
                    case R.id.nav_messages:
                        Toast.makeText(getApplicationContext(),"Messages clicked",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        //item.setChecked(true);
                        return true;
                }
                return false;
            }
        });
    }
}
