package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauep.digitalsensesnews.R;

import org.w3c.dom.Text;

/**
 * Created by CaueGarciaPolimanti on 5/27/2017.
 */
public class FragmentNewsHeadline extends Fragment {
    public static final String ARG_OBJECT = "object";

    // Views
    public TextView textViewNewsHeadline;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_news, container, false);
        Bundle args = getArguments();
        textViewNewsHeadline = ((TextView) rootView.findViewById(R.id.textView_news_headline));

        textViewNewsHeadline.setText( // android.R.id.text1
                "News number " + Integer.toString(args.getInt(ARG_OBJECT)));
        rootView.setContentDescription(textViewNewsHeadline.getText());
        textViewNewsHeadline.setContentDescription(textViewNewsHeadline.getText());
        textViewNewsHeadline.setFocusableInTouchMode(true);
        textViewNewsHeadline.setFocusable(true);
        return rootView;
    }
}
