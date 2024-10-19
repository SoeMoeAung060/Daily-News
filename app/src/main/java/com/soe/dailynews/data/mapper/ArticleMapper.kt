package com.soe.dailynews.data.mapper

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



//fun ArticleEntity.toArticle() : Article{
//    return Article(
//        source = source,
//        author = author,
//        title = title,
//        description = description,
//        url = url,
//        urlToImage = urlToImage,
//        publishedAt = publishedAt,
//        content = content
//    )
//}