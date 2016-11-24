package com.notarazi.myviewpagertablayout1.adapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.notarazi.myviewpagertablayout1.fragment.EmpAddFragment;
import com.notarazi.myviewpagertablayout1.fragment.EmpListFragment;
import com.notarazi.myviewpagertablayout1.fragment.FragmentPage3;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Employee", "Department", "Unit", "ADD" };
    private Context context;
    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int arg0) {
        Bundle data = new Bundle();
        switch(arg0){
            /** tab1 is selected */
            case 0:
                EmpListFragment fragmentpage2EmpList = new EmpListFragment();
                return fragmentpage2EmpList;
            /** tab2 is selected */
            case 1:
                FragmentPage3 fragmentpage3 = new FragmentPage3();
                return fragmentpage3;
            /** tab3 is selected */
            case 2:
                FragmentPage3 fragmentpage4 = new FragmentPage3();
                return fragmentpage4;
            case 3:
                EmpAddFragment fragmentpage1EmpAdd = new EmpAddFragment();
                return fragmentpage1EmpAdd;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
