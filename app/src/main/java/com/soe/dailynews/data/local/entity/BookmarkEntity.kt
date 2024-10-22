package com.soe.dailynews.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.soe.dailynews.domain.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class BookmarkEntity(
    @PrimaryKey
    val url: String,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String,
): Parcelable



@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable


