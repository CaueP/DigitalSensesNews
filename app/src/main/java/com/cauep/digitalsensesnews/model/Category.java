package com.cauep.digitalsensesnews.model;

import android.content.res.Resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mario on 27/05/2017.
 */

public class Category implements Serializable{

    @Expose
    @SerializedName("category")
    private String category;

    public Category(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription(){
        return "Click here to choose " + category + " category";
    }
}
