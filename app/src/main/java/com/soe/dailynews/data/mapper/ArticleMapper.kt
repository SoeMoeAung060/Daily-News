package com.soe.dailynews.data.mapper

import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.data.remote.dto.NewsResponseDTO
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.model.Source

fun NewsResponseDTO.toArticle() : List<Article> {
    return articles.map { article ->
        Article(
            source = article.source,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage ?: "",  // Handle potential nullability
            publishedAt = article.publishedAt,
            content = article.content ?: ""  // Handle potential nullability
        )
    }
}



fun Article.toBookmarkArticle() : BookmarkEntity{
    return BookmarkEntity(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}



fun BookmarkEntity.toArticle() : Article{
    return Article(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}