package org.example.data;

import com.google.gson.Gson;
import org.example.data.models.NewsData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewsDataSource {
    private final String filePath;
    private  static Gson gson = new Gson();

    public NewsDataSource(String filePath){
        this.filePath = filePath;
    }
    public NewsData readData(){
        try{
            String newsDataJsonString = Files.readString(Path.of("src/main/java/org/example/news_data.json"));
            return  gson.fromJson(newsDataJsonString, NewsData.class);
        }catch(IOException e){
            return null;
        }

    }
}
