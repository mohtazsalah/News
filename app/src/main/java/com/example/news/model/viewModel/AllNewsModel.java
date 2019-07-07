package com.example.news.model.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.news.model.db.ArticleEntity;
import com.example.news.model.db.Repository;

import java.util.List;

public class AllNewsModel extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<List<ArticleEntity>> mAllArticle;

   public AllNewsModel(Application application) {
       super(application);
      mRepository = new Repository(application);
      mAllArticle = mRepository.getAllArticle();
   }

    public LiveData<List<ArticleEntity>> getAllArticle() {
       return mAllArticle;
   }

    public void insert(ArticleEntity articleEntity) {
       mRepository.insert(articleEntity);
   }
}

