package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.utils.Constants;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 *          Created on 05/27/2017
 */

public class FragmentNews extends Fragment {
    static final String TAG = "FragmentNews";

    // Views
    View rootView;
    public TextView textViewNewsTitle;
    public TextView textViewNewsBody;
    public TextView textViewNewsAuthors;
    public TextView textViewNewsPublishDate;
    public ImageView imageViewNewsImage;

    public FragmentNews() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "Entered in onCreateView");
        setRetainInstance(true);
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        rootView = inflater.inflate(
                R.layout.fragment_news, container, false);

        // Restore News from Bundle of arguments
        Bundle args = getArguments();
        News news = (News) args.getSerializable(Constants.KEY.NEWS);

        findViews();

        if (news != null) {
            textViewNewsTitle.setText(news.getTitle());

            if (news.getAuthors().length > 0)
                textViewNewsAuthors.setText(getString(R.string.news_authors, news.getAuthors()[0]));
            else
                textViewNewsAuthors.setMaxHeight(0);

            textViewNewsPublishDate.setText(getString(R.string.news_publish_date, news.getPublishDate()));

            if (news.getImageSrc() != null || news.getImageSrc().equals("")) {
                Glide.with(rootView.getContext())
                        .load(news.getImageSrc())
                        .into(imageViewNewsImage);
            } else imageViewNewsImage.setMaxHeight(0);

            textViewNewsBody.setText(news.getBody());

            textViewNewsTitle.setContentDescription(textViewNewsTitle.getText());

            textViewNewsTitle.setContentDescription(textViewNewsBody.getText());
            textViewNewsTitle.setFocusableInTouchMode(true);
            textViewNewsTitle.setFocusable(true);
        } else Log.d(TAG, "NEWS IS NULL");

        return rootView;
    }

    private void findViews() {
        textViewNewsTitle = ((TextView) rootView.findViewById(R.id.textView_news_title));
        textViewNewsBody = ((TextView) rootView.findViewById(R.id.textView_news_body));
        textViewNewsAuthors = (TextView) rootView.findViewById(R.id.textView_news_authors);
        textViewNewsPublishDate = (TextView) rootView.findViewById(R.id.textView_news_publish_date);
        imageViewNewsImage = (ImageView) rootView.findViewById(R.id.imageView_news_image);
    }
}
