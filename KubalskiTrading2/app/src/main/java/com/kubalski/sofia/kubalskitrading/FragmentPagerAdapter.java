package com.kubalski.sofia.kubalskitrading;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by sofia on 2016-05-15.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private List<Fragment> lf;

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> lf){
        super(fm);
        this.lf = lf;

    }

    @Override
    public int getCount() {
        return  lf.size();
    }

    @Override
    public Fragment getItem(int position) {
        return lf.get(position);
    }
}
