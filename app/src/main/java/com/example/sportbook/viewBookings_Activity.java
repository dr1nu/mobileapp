package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;



public class viewBookings_Activity extends AppCompatActivity {
    private TabLayout tabBar;
    private ViewPager viewPager;
    private TabItem football,tennis,basketball;
    public  viewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings_);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout= findViewById(R.id.tabBar);
        football= findViewById(R.id.football);
        tennis= findViewById(R.id.tennis);
        basketball=findViewById(R.id.basketball);


        viewPager= findViewById(R.id.viewpager);
        pagerAdapter= new viewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.booking_menu,menu);
        return true;
    }
    public void selectMenuOption(MenuItem item){
        Intent intent = new Intent(viewBookings_Activity.this,booking.class);
        startActivity(intent);
    }


}
