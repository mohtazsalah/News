package com.example.news.model.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.news.model.Article;

@Entity
public class ArticleEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String url;

    private String urlToImage;

    private String publishedAt;

    public int favorite = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public static ArticleEntity getArticleEntity(Article article){
        if (article==null)
            return null;

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(article.getTitle());
        articleEntity.setUrlToImage(article.getUrlToImage());
        articleEntity.setUrl(article.getUrl());
        articleEntity.setPublishedAt(article.getPublishedAt());
        articleEntity.setDescription(article.getDescription());
        articleEntity.setFavorite(article.getFavorite());
        articleEntity.setId(article.getId());

        return articleEntity;
    }
    public static Article getArticle(ArticleEntity articleEntity){
        Article article = new Article();

        article.setPublishedAt(articleEntity.getPublishedAt());
        article.setDescription(articleEntity.getDescription());
        article.setUrlToImage(articleEntity.getUrlToImage());
        article.setUrl(articleEntity.getUrl());
        article.setTitle(articleEntity.getTitle());
        article.setFavorite(articleEntity.getFavorite());
        article.setId(articleEntity.getId());

        return article;
    }

}

