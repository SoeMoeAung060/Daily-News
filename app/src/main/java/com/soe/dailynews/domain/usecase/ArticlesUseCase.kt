package com.soe.dailynews.domain.usecase

import androidx.paging.PagingData
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow


data class ArticlesUseCase(
    val getBreakingNews : GetBreakingNews,
    val getNewsEverything : GetNewsEverything,
)


class GetBreakingNews(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(country : String) : Flow<PagingData<Article>>{
        return articleRepository.getBreakingNews(country)
    }

}

class GetNewsEverything(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(source : List<String>) : Flow<PagingData<Article>>{
        return articleRepository.getNewsEverything(source)
    }

}



//
//class UpsertArticle(
//    private val newsDao: NewsDao,
//){
//    suspend operator fun invoke(article: Article){
//
//        if (article.source.name.isNotEmpty()){
//            newsDao.insert(article)
//        }else{
//            Log.e("UpsertArticle", "Source name is null.")
//        }
//    }
//}
//
//class DeleteArticle(
//    private val newsDao: NewsDao
//) {
//    suspend operator fun invoke(article: Article) {
//        newsDao.delete(article)
//    }
//}
//
//class GetArticle(
//    private val newsDao: NewsDao
//){
//    suspend operator fun invoke(url : String) : Article?{
//        return newsDao.getArticle(url = url)
//    }
//}
//
//
//class GetArticles(
//    private val newsDao: NewsDao
//){
//    operator fun invoke() : Flow<List<Article>>{
//        return newsDao.getArticles()
//    }

//}