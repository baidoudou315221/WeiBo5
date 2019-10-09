package com.example.weibo5.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import lib.com.astuetz.PagerSlidingTabStrip;

public class PenAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.TitleIconTabProvider {
    private List<Fragment> mfragmentList;
    private List<String> mTitles;
    private List<Integer> mPic;
    public PenAdapter(FragmentManager fm,List<Fragment> list ,List<String> titles,List<Integer> pic) {
        super(fm);
        mfragmentList = list;
        mTitles = titles;
        mPic = pic;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }


    @Override
    public int getPageIconResId(int i) {
        return mPic.get(i);
    }


}
