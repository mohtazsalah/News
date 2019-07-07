package com.example.news.network;

import com.example.news.model.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("top-headlines?country=eg&pageSize=20&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> getAll();

    @GET("top-headlines?country=eg&pageSize=20&category=sports&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> getSport();

    @GET("top-headlines?country=eg&pageSize=20&category=technology&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> gettechnology();

    @GET("top-headlines?country=eg&pageSize=20&category=health&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> gethealth();

    @GET("top-headlines?country=eg&pageSize=20&category=business&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> getbusiness();

    @GET("top-headlines?country=eg&pageSize=20&category=science&apiKey=b08c257bac2c4ef6a269469d85eac98d")
    Call<News> getscience();

}
