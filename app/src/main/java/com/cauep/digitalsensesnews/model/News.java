package com.cauep.digitalsensesnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 * Created on 05/27/2017
 */

public class News implements Serializable{

    @Expose
    @SerializedName("title")
    private String headline;
    private String body;
    private String date;

    // Constructor
    public News(String headline, String body, String date) {
        this.headline = headline;
        this.body = body;
        this.date = date;
    }

    // Getters and Setters
    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
