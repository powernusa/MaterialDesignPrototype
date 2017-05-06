package com.powernusa.andy.mycheese;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidget();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);  //arrow sign appears
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        if(navView != null){
            setupDrawerContent(navView);
        }

        ViewPager vp = (ViewPager) findViewById(R.id.view_pager);
        if(vp!=null){
            setupViewPager(vp);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vp);

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
     * Fragment Pager Adapter
     *
     * *********************************************************************************************
     */

    public class CheeseFragmentPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mFragmentTitles = new ArrayList<>();

        public CheeseFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            if(fragment != null){
                mFragments.add(fragment);
                mFragmentTitles.add(title);
            }

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    /**
     * *********************************************************************************************
     *
     * Initialization and setting up
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

    private void setupViewPager(ViewPager vp){
        CheeseFragmentPagerAdapter adapter = new CheeseFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(),"Category 1");
        adapter.addFragment(new CheeseListFragment(),"Category 2");
        adapter.addFragment(new CheeseListFragment(),"Category 3");
        vp.setAdapter(adapter);
    }
}
