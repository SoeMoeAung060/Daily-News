package com.soe.dailynews.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeyEntity (
    @PrimaryKey(autoGenerate = false)  val url : String,
    @ColumnInfo(name = "prev_page") val prevPage : Int?,
    @ColumnInfo(name = "next_page") val nextPage : Int?
)