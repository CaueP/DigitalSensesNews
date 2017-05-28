package com.cauep.digitalsensesnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mario on 27/05/2017.
 */

public class Category implements Serializable{

    @Expose
    @SerializedName("title")
    private String title;

    public Category(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
