package com.example.android.tourguideapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
           return new CuisineFragment();
        } else if (position == 1) {
            return new EventFragment();
        } else if (position == 2) {
            return new NewsFragment();
        } else {
            return new TrailsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_cuisine);
        } else if (position == 1) {
            return mContext.getString(R.string.category_events);
        } else if (position == 2) {
            return mContext.getString(R.string.category_news);
        } else {
            return mContext.getString(R.string.category_trails);
        }
    }
}
