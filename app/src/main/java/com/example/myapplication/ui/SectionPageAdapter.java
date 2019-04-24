package com.example.myapplication.ui;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionPageAdapter";

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentsTitlelList = new ArrayList<>();


    public void addFragment(Fragment fragment, String title)   {
        mFragmentList.add(fragment);
        mFragmentsTitlelList.add(title);
    }

    public SectionPageAdapter(FragmentManager fm) { super(fm); }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitlelList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
