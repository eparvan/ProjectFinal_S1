package org.example.data.models;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("full_description")
    public String fullDescription;

}
