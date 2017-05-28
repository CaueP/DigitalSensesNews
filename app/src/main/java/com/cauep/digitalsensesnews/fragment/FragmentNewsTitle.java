package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.utils.Constants;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 *          Created on 05/27/2017
 */
public class FragmentNewsTitle extends Fragment {
    static final String TAG = "FragmentNewsTitle";

    // Views
    public TextView textViewNewsTitle;

    // Data
    private News news;
    private String newsIndex;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_news_title, container, false);
        Bundle args = getArguments();

        if (args != null) {
            news = (News) args.getSerializable(Constants.KEY.NEWS);
            newsIndex = Integer.toString(args.getInt(Constants.KEY.NEWS_ITEM_INDEX));
        }

        Log.d(TAG, "News Index: " + newsIndex);

        textViewNewsTitle = ((TextView) rootView.findViewById(R.id.textView_news_title));

        if (news != null) {
            textViewNewsTitle.setText(news.getTitle());
            rootView.setContentDescription(textViewNewsTitle.getText());
            textViewNewsTitle.setContentDescription(textViewNewsTitle.getText());
            textViewNewsTitle.setFocusableInTouchMode(true);
            textViewNewsTitle.setFocusable(true);
        } else Log.d(TAG, "NEWS IS NULL");

//        textViewNewsTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "CLICKED ON ON CLICK LISTENER");
//            }
//        });

        // Setting Tag to get this fragment on other activities
        rootView.setTag(TAG + newsIndex);
        return rootView;
    }
}
