package com.soe.dailynews.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article_entity")
@Parcelize
data class Article(

    @PrimaryKey
    val url: String,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String

) : Parcelable


@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable