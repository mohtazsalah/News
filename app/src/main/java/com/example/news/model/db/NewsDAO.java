package com.example.news.model.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDAO {

    @Query("SELECT * FROM articleentity")
    LiveData<List<ArticleEntity>> getAllNews();

//    @Query("SELECT * FROM articleentity where favorite== 1")
//    LiveData<List<ArticleEntity>> getFavoriteNews();
//
//    @Query("UPDATE articleentity SET favorite = 1 where id =:Id")
//    void setFavorite(int Id);
//
//    @Query("UPDATE articleentity SET favorite = 0 where id =:Id")
//    void deletFavorite(int Id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleEntity articleEntity);

}
