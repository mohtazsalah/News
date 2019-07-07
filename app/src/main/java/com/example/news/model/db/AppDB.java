package com.example.news.model.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleEntity.class},version = 1,exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    public abstract NewsDAO newsDAO();

    private static volatile AppDB INSTANCE;

    public static AppDB getDatabase(final Context context) {

        if (INSTANCE == null) {
            // synchronized to avoid bad threading
            synchronized (AppDB.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDB.class, "DB")
                        .build();
            }
        }

        return INSTANCE;
    }

}
