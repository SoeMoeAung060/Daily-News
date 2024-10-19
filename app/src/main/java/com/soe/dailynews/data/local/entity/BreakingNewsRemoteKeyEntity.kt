package com.soe.dailynews.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreakingNewsRemoteKeyEntity (
    @PrimaryKey(autoGenerate = false)  val url : String,
    @ColumnInfo(name = "next_page") val nextPage : Int?,
    @ColumnInfo(name = "prev_page") val prevPage : Int?
)