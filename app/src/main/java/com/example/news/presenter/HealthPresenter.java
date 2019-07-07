package com.example.news.presenter;

import com.example.news.contract.PresenterContract.CategoryContract;
import com.example.news.model.News;
import com.example.news.network.APIInterface;
import com.example.news.network.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthPresenter {

    private CategoryContract view;

    public HealthPresenter(CategoryContract view) {
        this.view = view;
    }

    public void getHealth(){
        APIInterface apiInterface = Retrofit.getClint().create(APIInterface.class);
        Call<News> call = apiInterface.gethealth();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                view.getHealth(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                view.stopLoading();
                view.noInternetConnection("connection error");
            }
        });
    }
}
