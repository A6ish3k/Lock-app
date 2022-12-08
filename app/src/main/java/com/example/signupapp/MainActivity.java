package com.example.signupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Assigning keys to ids
    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calling keys by ids
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //Calling viewPager adapter with fragment manager
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //Setting up viewPager(with tabs)
        tabLayout.setupWithViewPager(viewPager);
    }
}