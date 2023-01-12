package org.example.domain;

import org.example.data.NewsDataSource;
import org.example.data.models.Article;
import org.example.data.models.NewsData;

public class NewsParser {
    private NewsData newsData;
    public NewsParser(NewsDataSource newsDataSource){
        newsData = newsDataSource.readData();
    }
   public Integer getNumberOfArticles(){
        return newsData.totalResults;
    }

    public void printAllArticles(){

    }

    public void printArticleById(Integer id){

    }
    public void printArticleDescriptionById(Integer id){

    }
    public void printArticleFullDescriptionById(Integer id){

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
