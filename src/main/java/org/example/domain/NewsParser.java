package org.example.domain;

import org.example.data.NewsDataSource;
import org.example.data.models.Article;
import org.example.data.models.NewsData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NewsParser {
    private NewsData newsData;
    public NewsParser(NewsDataSource newsDataSource){
        newsData = newsDataSource.readData();
    }
   public Integer getNumberOfArticles(){
        return newsData.totalResults;
    }

    public List<String> printAllArticles(){
       List<String> titleArticle = new ArrayList<>();
       for(Article item: newsData.articles){
           titleArticle.add(item.title);
       }
       return titleArticle;
    }


    public List<String> printArticleDescriptionById(Integer id){
        List<String> descriptionArticle = new ArrayList<>();
        for(Article item: newsData.articles){
            descriptionArticle.add(item.description);
        }
        return descriptionArticle;
    }
    public List<String> printArticleFullDescriptionById(Integer id){
        List<String> descriptionArticle = new ArrayList<>();
        for(Article item: newsData.articles){
            descriptionArticle.add(item.fullDescription);
        }
        return descriptionArticle;
    }

    public List<String> searchByKeywords(String key){
        List<String> listArticle = new ArrayList<>();
       for(int i = 0; i<newsData.articles.size(); i++){
           if(newsData.articles.get(i).keywords !=null) {
               if (newsData.articles.get(i).keywords.contains(key)) {
                   listArticle.add(newsData.articles.get(i).title);
               }
           }
        }
       Collections.sort(listArticle, Collections.reverseOrder());
        return listArticle;
    }

    public Integer getTotalWordsCount() {
        Integer result = 0;
        for(Article article : newsData.articles){
            result = getWordsCountForArticle(article);
        }
        return result;
    }

    public Integer getWordsCountForArticle(int articleId) {
        Article article = newsData.articles.get(articleId);
        return getWordsCountForArticle(article);
    }
    private Integer getWordsCountForArticle(Article article){
        Integer result = 0;
        result+=getsWordsCount(article.title);
        result+=getsWordsCount(article.description);
        result+=getsWordsCount(article.fullDescription);
        return result;
    }

    private Integer getsWordsCount(String text){
        if(text == null || text.isBlank()){
            return 0;
        }else{
            return text.split(" ").length;
        }
    }
}
