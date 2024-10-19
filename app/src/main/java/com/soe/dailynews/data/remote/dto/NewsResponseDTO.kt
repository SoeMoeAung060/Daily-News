package com.soe.dailynews.data.remote.dto

import com.soe.dailynews.domain.model.Article

data class NewsResponseDTO(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
