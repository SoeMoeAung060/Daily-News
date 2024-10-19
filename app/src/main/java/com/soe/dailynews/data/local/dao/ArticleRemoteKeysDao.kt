package com.soe.dailynews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soe.dailynews.data.local.entity.ArticleRemoteKeyEntity

@Dao
interface ArticleRemoteKeysDao {

    @Query("SELECT * FROM ArticleRemoteKeyEntity WHERE url = :url")
     fun getRemoteKeys(url: String): ArticleRemoteKeyEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAllRemoteKeys(remoteKeys: List<ArticleRemoteKeyEntity>)

    @Query("DELETE FROM ArticleRemoteKeyEntity")
     fun deleteAllRemoteKeys()

}