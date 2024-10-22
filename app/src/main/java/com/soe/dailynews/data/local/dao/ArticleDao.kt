package com.soe.dailynews.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM Article")
    fun getAllArticle(): PagingSource<Int, Article>

    @Query("DELETE FROM Article")
    fun deleteAll()



    // Get a specific bookmark by URL
    @Query("SELECT * FROM BookmarkEntity WHERE url = :url")
    suspend fun getBookmarkByUrl(url: String): BookmarkEntity?

    // Get all bookmarked articles as a Flow
    @Query("SELECT * FROM BookmarkEntity")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    // Delete a specific bookmark
    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)

    // Insert or update a bookmark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBookmark(bookmark: BookmarkEntity)





    @Query("SELECT * FROM Article WHERE url = :url")
    suspend fun getArticle(url : String) : Article?

    @Delete
    suspend fun delete(articles: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articles: Article)


    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>





}