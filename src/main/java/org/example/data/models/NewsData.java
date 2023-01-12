package org.example.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsData {
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public Integer totalResults;
    @SerializedName("results")
    public List<Article> articles;
    @SerializedName("nextPage")
    public Integer nextPage;
}
