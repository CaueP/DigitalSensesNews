package com.cauep.digitalsensesnews.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cauep.digitalsensesnews.fragment.FragmentNewsHeadline;

/**
 * Created by CaueGarciaPolimanti on 5/27/2017.
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new FragmentNewsHeadline();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(FragmentNewsHeadline.ARG_OBJECT, i + 1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

}
