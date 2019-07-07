package com.example.news.contract.PresenterContract;

import com.example.news.model.Article;

import java.util.List;

public interface ArticleInterFace extends BaiseInterFacePresenter{

    void getArticle(List<Article> list);

}
