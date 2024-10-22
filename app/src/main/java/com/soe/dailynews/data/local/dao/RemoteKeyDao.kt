package com.soe.dailynews.data.local.dao


import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soe.dailynews.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeysDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllRemoteKeys(remoteKeys: List<RemoteKeyEntity>)

    @Query("SELECT * FROM RemoteKeyEntity WHERE url = :url")
    fun getRemoteKeys(url: String): RemoteKeyEntity?

    @Query("DELETE FROM RemoteKeyEntity")
    fun deleteAllRemoteKeys()

}