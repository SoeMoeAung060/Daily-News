package com.soe.dailynews.data.local.dao

import android.util.Log
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soe.dailynews.domain.model.Article

@Dao
interface ArticleDao {


    @Query("SELECT * FROM article_entity")
     fun getAllArticle(): PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article_entity")
     fun clearAll()



}