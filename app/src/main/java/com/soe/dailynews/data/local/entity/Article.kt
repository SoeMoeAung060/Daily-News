//package com.soe.dailynews.data.local.entity
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.soe.dailynews.domain.model.Source
//
//@Entity(tableName = "article_entity")
//data class ArticleEntity(
//    @ColumnInfo(name = "source") val source : Source,
//    @ColumnInfo(name= "author") val author : String?,
//    @ColumnInfo(name = "title") val title : String,
//    @ColumnInfo(name = "description") val description : String,
//    @PrimaryKey
//    @ColumnInfo(name = "url") val url : String,
//    @ColumnInfo(name = "urlToImage") val urlToImage : String,
//    @ColumnInfo(name = "publishedAt") val publishedAt : String,
//    @ColumnInfo(name = "content") val content : String
//)
//
//data class Source(
//    val id : String?,
//    val name : String
//)