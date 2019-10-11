package com.example.android.tourguideapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager viewPager = findViewById(R.id.swipePager);
//
        CustomViewPager mViewPager;
        mViewPager = (CustomViewPager) findViewById(R.id.swipePager);
        mViewPager.setOffscreenPageLimit(0);
        SectionAdapter adapter = new SectionAdapter(this, getSupportFragmentManager());
        // Set the adapter onto the view pager
        mViewPager.setAdapter(adapter);

//        TabLayout tabLayout = findViewById(R.id.tabs);
//
//        tabLayout.setupWithViewPager(viewPager);
    }
}
