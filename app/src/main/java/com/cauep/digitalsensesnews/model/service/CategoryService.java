package com.cauep.digitalsensesnews.model.service;


import com.cauep.digitalsensesnews.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mario on 27/05/2017.
 */

public interface CategoryService {

    @GET("news/categories")
    Call<ArrayList<Category>> getCategories();
}
