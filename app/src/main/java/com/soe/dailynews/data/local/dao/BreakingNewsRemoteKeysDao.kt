package com.soe.dailynews.data.local.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soe.dailynews.data.local.entity.BreakingNewsRemoteKeyEntity

@Dao
interface BreakingNewsRemoteKeysDao {

    @Query("SELECT * FROM BreakingNewsRemoteKeyEntity WHERE url = :url")
    fun getRemoteKeys(url: String): BreakingNewsRemoteKeyEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllRemoteKeys(remoteKeys: List<BreakingNewsRemoteKeyEntity>){
         Log.d("BreakingNewsRemoteKeysDao", "addAllRemoteKeys: $remoteKeys")
     }


    @Query("DELETE FROM BreakingNewsRemoteKeyEntity")
    fun deleteAllRemoteKeys()

}