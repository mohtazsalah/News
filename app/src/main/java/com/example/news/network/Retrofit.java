package com.example.news.network;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static retrofit2.Retrofit retrofit = null;

    public static retrofit2.Retrofit getClint() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
