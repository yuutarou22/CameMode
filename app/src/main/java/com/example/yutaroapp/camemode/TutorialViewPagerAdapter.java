package com.example.yutaroapp.camemode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yutaroapp.camemode.TutorialPagerFragment.TutorialPagerFragment1;
import com.example.yutaroapp.camemode.TutorialPagerFragment.TutorialPagerFragment2;
import com.example.yutaroapp.camemode.TutorialPagerFragment.TutorialPagerFragment3;

public class TutorialViewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_NUM = 3;

    public TutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int potision) {
        Fragment fragment = null;
        switch (potision) {
            case 0:
                fragment = new TutorialPagerFragment1();
                break;
            case 1:
                fragment = new TutorialPagerFragment2();
                break;
            default:
                fragment = new TutorialPagerFragment3();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
