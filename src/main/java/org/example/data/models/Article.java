package org.example.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("full_description")
    public String fullDescription;
    @SerializedName("keywords")
    public List<String> keywords;
    @SerializedName("creator")
    public List<String> creator;
}
