package com.example.news.contract.PresenterContract;

import com.example.news.model.News;


public interface CategoryContract extends BaiseInterFacePresenter{

    void getSport(News sportNews);
    void getBusiness(News businessNews);
    void getTechnology(News technologyNews);
    void getHealth(News healthNews);
    void getScience(News scienceNews);
}
