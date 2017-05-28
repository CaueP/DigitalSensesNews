package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.controller.NewsPagerAdapter;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.utils.Constants;

import java.util.ArrayList;


/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 * Created on 05/27/2017
 */

public class FragmentNewsPager extends Fragment {
    static String TAG = "FragmentNewsPager";

    // View Pager adapter
    NewsPagerAdapter mNewsPagerAdapter;
    ViewPager mViewPager;

    // Views
    View rootView = null;
    Button buttonPrevious, buttonNext;

    // Data
    ArrayList<News> newsList = null;

    /**
     * Fragment constructor
     */
    public FragmentNewsPager() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Entered in onCreateView");
        // Tell the framework to try to keep this fragment around
        setRetainInstance(true);

        rootView = inflater.inflate(R.layout.fragment_news_pager, container, false);

        // Restore News from Bundle of arguments
        Bundle args = getArguments();
        if(args != null) {
            newsList = (ArrayList<News>) args.getSerializable(Constants.KEY.NEWS_LIST);
            if(mNewsPagerAdapter != null) mNewsPagerAdapter = null;
        } else{
            Log.d(TAG, "Bundle com lista de News vazio");
        }


        if(newsList != null) {
            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.
            mNewsPagerAdapter =
                    new NewsPagerAdapter(
                            getActivity().getSupportFragmentManager(), newsList);
            mViewPager = (ViewPager) rootView.findViewById(R.id.pager_news);
            mViewPager.setAdapter(mNewsPagerAdapter);
        }

        findViews();
        setListeners();

        return rootView;
    }

    /**
     * Find all the views on the page and attach to the view variables
     */
    private void findViews() {
//        buttonPrevious = (Button) rootView.findViewById(R.id.button_previous);
//        buttonNext = (Button) rootView.findViewById(R.id.button_next);
    }

    /**
     * Set all the view listeners
     */
    private void setListeners() {

        // onClick Listeners
//        buttonPrevious.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mViewPager.getCurrentItem() == 0) {
//                    Toast.makeText(getActivity(), R.string.msg_first_item, Toast.LENGTH_SHORT).show();
//                } else {
//                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
//                }
//            }
//        });
//
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mViewPager.getCurrentItem() == mNewsPagerAdapter.getCount() - 1) {
//                    mViewPager.setCurrentItem(0);
//                } else {
//                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
//                }
//            }
//        });


        // PageChangeListeners
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.d(TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged");

                if (state == ViewPager.SCROLL_STATE_SETTLING)
                    //Log.d(TAG, "onPageScrollStateChanged SCROLL_STATE_SETTLING");
                if (state == ViewPager.SCROLL_STATE_DRAGGING)
                    //Log.d(TAG, "onPageScrollStateChanged SCROLL_STATE_DRAGGING");

                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.d(TAG, "onPageScrollStateChanged SCROLL_STATE_IDLE");
                    View currentView = mNewsPagerAdapter.getItem(mViewPager.getCurrentItem()).getView();
                    //FragmentNewsHeadline frag = (FragmentNewsHeadline) mNewsPagerAdapter.getItem(mViewPager.getCurrentItem());


                    // trying to get from fragment
//                    View fragView = getActivity().getSupportFragmentManager().findFragmentByTag(Integer.toString(mViewPager.getCurrentItem()+1)).getView();
//                    TextView textViewNewsHeadling = (TextView) fragView.findViewById(R.id.textView_news_headline);
//                    String newsHeadline = textViewNewsHeadling.getText().toString();

                    // getting from rootView
//                    TextView textViewNewsHeadling = (TextView) rootView.findViewById(R.id.textView_news_headline);
//                    String newsHeadline = ((TextView) rootView.findViewById(R.id.textView_news_headline)).getText().toString();

                    // Show toast message
//                    Toast.makeText(getActivity(), newsHeadline, Toast.LENGTH_LONG).show();
//
//                    textViewNewsHeadling.getRootView().setContentDescription(newsHeadline);
//                    textViewNewsHeadling.setContentDescription(newsHeadline);
//                    textViewNewsHeadling.setFocusableInTouchMode(true);
//                    textViewNewsHeadling.setFocusable(true);
                    checkNewsItemState();
                }
            }

        });


    }

    /**
     * Check the current item and shows message to the user if this is the first or last news
     */
    private void checkNewsItemState() {

        if (mViewPager.getCurrentItem() == 0) {
            Toast.makeText(getActivity(), R.string.msg_first_item, Toast.LENGTH_SHORT).show();
        }
        if (mViewPager.getCurrentItem() == mNewsPagerAdapter.getCount() - 1) {
            Toast.makeText(getActivity(), R.string.msg_last_item, Toast.LENGTH_SHORT).show();
        }
    }
}
