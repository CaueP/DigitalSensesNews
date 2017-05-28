package com.cauep.digitalsensesnews.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cauep.digitalsensesnews.fragment.FragmentNewsTitle;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.utils.Constants;

import java.util.ArrayList;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 * Created on 05/27/2017
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<News> newsList = null;

    public NewsPagerAdapter(FragmentManager fm, ArrayList<News> newsList) {
        super(fm);
        this.newsList = newsList;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new FragmentNewsTitle();
        Bundle args = new Bundle();

        // Passing our News object to the fragment
        args.putSerializable(Constants.KEY.NEWS, newsList.get(i));
        args.putInt(Constants.KEY.NEWS_ITEM_INDEX, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsList.get(position).getTitle();
    }

}
