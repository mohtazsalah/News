package com.example.news.presenter;

import com.example.news.contract.PresenterContract.ArticleInterFace;
import com.example.news.model.News;
import com.example.news.network.APIInterface;
import com.example.news.network.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlePresenter {

    private ArticleInterFace view;

    public ArticlePresenter(ArticleInterFace view) {
        this.view = view;
    }

    public void getArticles(){

        APIInterface apiInterface = Retrofit.getClint().create(APIInterface.class);
        Call<News> call = apiInterface.getAll();
        view.startLoading();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                view.stopLoading();
                view.getArticle(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                view.stopLoading();
                view.noInternetConnection("connection error");
            }
        });
    }
}
