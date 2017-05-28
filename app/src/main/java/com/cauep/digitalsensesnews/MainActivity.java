package com.cauep.digitalsensesnews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.cauep.digitalsensesnews.fragment.FragmentNewsPager;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.model.service.NewsService;
import com.cauep.digitalsensesnews.utils.Constants;
import com.cauep.digitalsensesnews.utils.ServiceGenerator;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 *          Created on 05/27/2017
 */
public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainPageActivity";


    // Create news API service
    NewsService newsService = ServiceGenerator.createService(NewsService.class);

    ArrayList<News> newsList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadNews("pt", "tech");
    }

    private void loadFakeNews(String language, String category){

    }

    /**
     * Load a news from web server
     * @param language
     * @param category
     */
    private void loadNews(String language, String category) {
        Call<ArrayList<News>> newsCall = newsService.getNews(language, category);

        Callback<ArrayList<News>> newsCallback = new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "loadNews - onResponse isSuccessful");
                    newsList = response.body();

                    if (newsList == null) {
                        Log.d(TAG, "loadNews - Body: " + response.body());
                    } else {
                        loadNewsPager(newsList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Log.d(TAG, "loadNews - onFailure: " + t.getMessage());
            }
        };

        newsCall.enqueue(newsCallback);
    }

    private void loadNewsPager(ArrayList<News> newsList) {

        Fragment newsPagerFragment = new FragmentNewsPager();
        if(newsList != null){
            Bundle menuBundle = new Bundle();
            menuBundle.putSerializable(Constants.KEY.NEWS_LIST, newsList);
            newsPagerFragment.setArguments(menuBundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, newsPagerFragment)
                //.addToBackStack(null)        // add to back stack
                .commit();
    }
}
