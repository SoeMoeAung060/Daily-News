package com.soe.dailynews.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soe.dailynews.data.local.NewsTypeConverter
import com.soe.dailynews.data.local.dao.ArticleRemoteKeysDao
import com.soe.dailynews.data.local.dao.ArticleDao
import com.soe.dailynews.data.local.entity.ArticleRemoteKeyEntity
import com.soe.dailynews.domain.model.Article


@Database(entities = [Article::class, ArticleRemoteKeyEntity::class], version = 1, exportSchema = true)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun articleDao() : ArticleDao
    abstract fun articleRemoteKeysDao(): ArticleRemoteKeysDao


}