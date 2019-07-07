package com.example.news;

import android.app.Application;

public class NewsApp extends Application {

    private static NewsApp app;

    public static NewsApp getInstance(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

}
