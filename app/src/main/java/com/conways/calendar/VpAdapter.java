package com.conways.calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Conways on 2017/3/3.
 */

public class VpAdapter extends FragmentPagerAdapter{
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CalendarFragment.newInstance(System.currentTimeMillis(),null);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
