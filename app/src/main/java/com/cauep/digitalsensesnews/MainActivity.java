package com.cauep.digitalsensesnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.cauep.digitalsensesnews.fragment.FragmentCategories;
import com.cauep.digitalsensesnews.fragment.FragmentNews;
import com.cauep.digitalsensesnews.fragment.FragmentNewsPager;
import com.cauep.digitalsensesnews.model.Category;
import com.cauep.digitalsensesnews.model.News;
import com.cauep.digitalsensesnews.model.service.CategoryService;
import com.cauep.digitalsensesnews.model.service.NewsService;
import com.cauep.digitalsensesnews.utils.Constants;
import com.cauep.digitalsensesnews.utils.ServiceGenerator;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 *          Created on 05/27/2017
 */
public class MainActivity extends AppCompatActivity
        implements FragmentNewsPager.OnListItemSelectedListener,
        FragmentCategories.OnCategorySelectedListener {
    final static String TAG = "MainPageActivity";

    // Create news API service
    NewsService newsService = ServiceGenerator.createService(NewsService.class);
    CategoryService categoryService = ServiceGenerator.createService(CategoryService.class);

    // View
    ProgressBar mainPageProgressBar;

    // Data
    ArrayList<News> newsList = null;
    ArrayList<Category> categoriesList = null;
    String systemLanguage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemLanguage = Locale.getDefault().getLanguage();
        Log.d(TAG, "==========A LINGUAGEM ATUAL É: " + systemLanguage);
        Log.d(TAG,"getISO3Language:  " + Locale.getDefault().getISO3Language());
        Log.d(TAG,"getDisplayLanguage:  " + Locale.getDefault().getDisplayLanguage());

        mainPageProgressBar = (ProgressBar) findViewById(R.id.main_page_progressBar);

        mainPageProgressBar.setVisibility(View.VISIBLE);

        loadCategories();
        //loadFakeNews("pt", "tech");
    }

    /*
    private void loadFakeNews(String language, String category){
        ArrayList<News> newsFakeList = new ArrayList<News>();

        for(int i = 0; i < 5; i++)
        newsFakeList.add(
                new News(i + " Pelé namora xuxa", "Pelé declara seu amor por xuxa e a loira corresponde.", "20/02/1970 - 10:40"));

        loadNewsPager(newsFakeList);
    }
    */

    private void loadCategories() {
        Call<ArrayList<Category>> categoriesCall = categoryService.getCategories();

        mainPageProgressBar.setVisibility(View.VISIBLE);

        Callback<ArrayList<Category>> categoriesCallback = new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                mainPageProgressBar.setVisibility(View.INVISIBLE);

                if (response.isSuccessful()) {
                    Log.d(TAG, "loadCategories - onResponse isSuccessful");
                    categoriesList = response.body();

                    if (categoriesList == null) {
                        Log.d(TAG, "loadCategories - Body: " + response.body());
                    } else {
                        loadCategoriesRV();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                mainPageProgressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "loadCategories - onFailure: " + t.getMessage());
            }
        };

        categoriesCall.enqueue(categoriesCallback);
    }

    /**
     * Load a news from web server
     *
     * @param language
     * @param category
     */
    private void loadNews(String language, String category) {
        Call<ArrayList<News>> newsCall = newsService.getNews(language, category);
        mainPageProgressBar.setVisibility(View.VISIBLE);

        Callback<ArrayList<News>> newsCallback = new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                mainPageProgressBar.setVisibility(View.INVISIBLE);
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
                mainPageProgressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "loadNews - onFailure: " + t.getMessage());
            }
        };

        newsCall.enqueue(newsCallback);
    }

    /**
     * Load the available categories on API
     */
    private void loadCategoriesRV() {
        Log.d(TAG, "loadCategoriesRV");
        Fragment categoriesFragment = new FragmentCategories();
        if (categoriesList != null) {
            Bundle menuBundle = new Bundle();
            menuBundle.putSerializable(Constants.KEY.CATEGORIES_LIST, categoriesList);
            categoriesFragment.setArguments(menuBundle);
            changeFragment(categoriesFragment);
        } else Log.d(TAG, "loadCategoriesRV - categoriesList == null");
    }

    /**
     * Change fragment showed on main activity
     *
     * @param fragment Fragment to be showed
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)        // add to back stack
                .commit();
    }

    /**
     * Load News PagerView with the list of news
     *
     * @param newsList List of news
     */
    private void loadNewsPager(ArrayList<News> newsList) {

        Fragment newsPagerFragment = new FragmentNewsPager();
        if (newsList != null) {
            Bundle menuBundle = new Bundle();
            menuBundle.putSerializable(Constants.KEY.NEWS_LIST, newsList);
            newsPagerFragment.setArguments(menuBundle);
            changeFragment(newsPagerFragment);
        }
    }

    /**
     * FragmentsNewPager Interface implementation
     *
     * @param itemPosition Item position on the list
     */
    @Override
    public void onListItemSelected(int itemPosition) {
        if (newsList != null) {
            Fragment newsFragment = new FragmentNews();
            if (newsList != null) {
                Bundle newsBundle = new Bundle();
                newsBundle.putSerializable(Constants.KEY.NEWS, newsList.get(itemPosition));
                newsFragment.setArguments(newsBundle);
                changeFragment(newsFragment);
            }
        }

    }

    /**
     * FragmentCategories Interface implementation
     *
     * @param itemPosition Item position on the list
     */
    @Override
    public void onCategorySelected(int itemPosition) {
        if (categoriesList != null && systemLanguage != null) {
            loadNews(systemLanguage, categoriesList.get(itemPosition).getCategory());
        }
    }
}
