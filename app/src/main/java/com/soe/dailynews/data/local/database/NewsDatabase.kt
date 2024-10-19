package com.soe.dailynews.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soe.dailynews.data.local.NewsTypeConverter
import com.soe.dailynews.data.local.dao.BreakingNewsRemoteKeysDao
import com.soe.dailynews.data.local.dao.ArticleDao
import com.soe.dailynews.data.local.entity.BreakingNewsRemoteKeyEntity
import com.soe.dailynews.domain.model.Article


@Database(entities = [
    Article::class,
    BreakingNewsRemoteKeyEntity::class], version = 1, exportSchema = true)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun articleDao() : ArticleDao
    abstract fun breakingNewsRemoteKeysDao(): BreakingNewsRemoteKeysDao


}