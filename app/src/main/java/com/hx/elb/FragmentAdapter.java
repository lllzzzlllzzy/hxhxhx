package com.hx.elb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Hello on 2020/10/13.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> mFragments;
    private List<String> mTtilts;

    public FragmentAdapter(FragmentManager fm, List<String> mTtilts, List<Fragment> mFragments) {
        super(fm);
        this.mTtilts = mTtilts;
        this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTtilts.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}
