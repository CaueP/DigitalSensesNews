package com.cauep.digitalsensesnews.model.service;

import com.cauep.digitalsensesnews.model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Cauê Garcia Polimanti
 * @version 1.0
 * Created on 5/27/2017.
 */

public interface NewsService {

    /**
     * Get a list of news
     * @param language Language of the resources (Available: pt, en)
     * @param category Category of the articles (Available: sports, economy, tech, health)
     * @return Return a List of News objects
     */
    @GET("news/{language}/{category}")
    Call<ArrayList<News>> getNews(@Path("language") String language, @Path("category") String category);
}
