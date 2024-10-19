package com.soe.dailynews.util

import androidx.paging.PagingData
import androidx.paging.filter
import com.soe.dailynews.domain.model.Article

fun PagingData<Article>.filterArticle() : PagingData<Article>{

    return this.filter { article ->
        val hasValidTitle = !article.title.isNullOrEmpty()
        val contentIsNotRemoved = article.content?.contains("[Removed]") == false
        val hasValidImage = !article.urlToImage.isNullOrEmpty()

        hasValidTitle && contentIsNotRemoved && hasValidImage

    }
}