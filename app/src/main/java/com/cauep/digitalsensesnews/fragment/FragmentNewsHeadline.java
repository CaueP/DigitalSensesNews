package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauep.digitalsensesnews.R;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 * Created on 05/27/2017
 */
public class FragmentNewsHeadline extends Fragment {
    static final String TAG = "FragmentNewsHeadline";
    public static final String ARG_OBJECT = "object";

    // Views
    public TextView textViewNewsHeadline;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_news_headline, container, false);
        Bundle args = getArguments();

        String newsId = "news" + Integer.toString(args.getInt(ARG_OBJECT));

        Log.d(TAG, "News ID: " + newsId);

        textViewNewsHeadline = ((TextView) rootView.findViewById(R.id.textView_news_headline));

        textViewNewsHeadline.setText(newsId);
        rootView.setContentDescription(textViewNewsHeadline.getText());
        textViewNewsHeadline.setContentDescription(textViewNewsHeadline.getText());
        textViewNewsHeadline.setFocusableInTouchMode(true);
        textViewNewsHeadline.setFocusable(true);

        // Setting Tag to get this fragment on other activities
        rootView.setTag(newsId);
        return rootView;
    }
}
