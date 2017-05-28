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
    private String title;

    @Expose
    @SerializedName("text")
    private String body;

    @Expose
    @SerializedName("top_image")
    private String imageSrc;

    @Expose
    @SerializedName("publish_date")
    private String publishDate;

    @Expose
    @SerializedName("authors")
    private String[] authors;

    // Constructor
    public News(String title, String body,
                String imageSrc, String publishDate,
                String[] authors) {
        this.title = title;
        this.body = body;
        this.imageSrc = imageSrc;
        this.publishDate = publishDate;
        this.authors = authors;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getPublishDate(){
        return publishDate;
    }

    public String[] getAuthors(){
        return authors;
    }

}
