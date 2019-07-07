package com.example.news.model.db;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class Repository {

    private NewsDAO newsDAO;
    private LiveData<List<ArticleEntity>> allArticle;

    public Repository(Application application) {
        AppDB db = AppDB.getDatabase(application);
        newsDAO = db.newsDAO();
        allArticle = newsDAO.getAllNews();
    }

    public LiveData<List<ArticleEntity>> getAllArticle() {
        return allArticle;
    }

    public void insert(final ArticleEntity articleEntity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsDAO.insert(articleEntity);
            }
        }).start();
    }
}