package com.soe.dailynews.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.soe.dailynews.data.local.dao.ArticleDao
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.data.mapper.toArticle
import com.soe.dailynews.data.mapper.toBookmarkArticle
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


data class ArticlesUseCase(
    val getNews : GetNews,
    val getTopHeadline : GetTopHeadline,

//    val getArticle: GetArticle,
//    val getArticles: GetArticles,
//    val upsertArticles: UpsertArticle,
//    val deleteArticle: DeleteArticle,

    val searchNews: SearchNews,
    val getCategoriesNews: GetCategoriesNews,



    val getAllBookmarkArticles: GetBookmarkArticles,
    val getBookmarkByUrl: GetBookmarkByUrl,
    val upsertBookmarkArticle: UpsertBookmarkArticle,
    val deleteBookmarkArticle: DeleteBookmarkArticle,

    )

class SearchNews(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(query : String, sources : List<String>) : Flow<PagingData<Article>>{
        return articleRepository.getSearchNews(query, sources)
    }
}


class GetCategoriesNews(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(category : String) : Flow<PagingData<Article>>{
        return articleRepository.getCategoriesNews(category)
    }
}


class GetNews(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(sources: List<String>) : Flow<PagingData<Article>>{
        return articleRepository.getNews(sources)
    }
}

class GetTopHeadline(
    private val articleRepository: ArticleRepository
){
    suspend operator fun invoke(sources: List<String>) : Flow<PagingData<Article>>{
        return articleRepository.getNews(sources)
    }
}



class GetBookmarkByUrl(
    private val articleDao: ArticleDao
){
    suspend operator fun invoke(url : String) : BookmarkEntity? {
        return articleDao.getBookmarkByUrl(url)
    }
}


class GetBookmarkArticles(
    private val articleDao: ArticleDao
){
    operator fun invoke(): Flow<List<Article>> {
        return articleDao.getAllBookmarks().map { bookmarkEntity ->
            bookmarkEntity.map {
                it.toArticle() // Convert each BookmarkEntity to Article
            }

        }
    }
}


class UpsertBookmarkArticle(
    private val articleDao: ArticleDao){
    suspend operator fun invoke(bookmarkEntity: BookmarkEntity){
        articleDao.upsertBookmark(bookmarkEntity)
    }
}


class DeleteBookmarkArticle(
    private val articleDao: ArticleDao
) {
    suspend operator fun invoke(bookmarkEntity: BookmarkEntity) {
        articleDao.deleteBookmark(bookmarkEntity)
    }
}









//
//
//
//class GetArticle(
//    private val articleDao: ArticleDao
//){
//    suspend operator fun invoke(url : String) : Article? {
//        return articleDao.getArticle(url)
//    }
//}
//
//
//class GetArticles(
//    private val articleDao: ArticleDao
//){
//    operator fun invoke(): Flow<List<Article>> {
//        return articleDao.getArticles()
//    }
//}
//
//
//class UpsertArticle(
//    private val articleDao: ArticleDao
//){
//    suspend operator fun invoke(article: Article){
//        if (article.source.name.isNotEmpty()){
//            articleDao.upsert(article)
//        }else{
//            Log.e("upsertArticles", "upsertArticle is null.")
//        }
//    }
//}




class DeleteArticle(
    private val articleDao: ArticleDao
) {
    suspend operator fun invoke(article: Article) {
        articleDao.delete(article)
    }
}




// Bookmark

//
//class GetBookmarkArticles(
//    private val bookmarkDao: BookmarkDao
//){
//    suspend operator fun invoke(url : String) : Article? {
//        return bookmarkDao.getBookmarkArticles(url)
//    }
//}
//
//
//class GetBookmarkArticle(
//    private val bookmarkDao: BookmarkDao
//){
//    operator fun invoke(): Flow<List<Article>> {
//        return bookmarkDao.getBookmarkArticle()
//    }
//}
//
//
//class UpsertBookmarkArticle(
//    private val bookmarkDao: BookmarkDao
//){
//    suspend operator fun invoke(article: Article){
//        if (article.source.name.isNotEmpty()){
//            bookmarkDao.upsertBookmarkArticles(article)
//        }else{
//            Log.e("upsertArticles", "upsertArticle is null.")
//        }
//    }
//}
//
//
//
//
//class DeleteBookmarkArticle(
//    private val bookmarkDao: BookmarkDao
//) {
//    suspend operator fun invoke(article: Article) {
//        bookmarkDao.deleteBookmarkArticles(article)
//    }
//}

//
//class GetBookmarkArticles(
//    private val articleDao: ArticleDao
//){
//    operator fun invoke(): Flow<List<Article>> {
//        return articleDao.getBookmarkArticles()
//    }
//}
//
//
//class UpdateBookmarkArticles(
//    private val articleDao: ArticleDao
//){
//    suspend operator fun invoke(url: String, isBookmark: Boolean ) {
//        articleDao.upDateBookmarkArticles(url, isBookmark)
//    }
//}



//
//class GetBreakingNews(
//    private val articleRepository: ArticleRepository
//){
//    suspend operator fun invoke(country : String) : Flow<PagingData<Article>>{
//        return articleRepository.getBreakingNews(country)
//    }
//
//}
//
//class GetEverythingNews(
//    private val articleRepository: ArticleRepository
//){
//    suspend operator fun invoke(sources : List<String>, country: String) : Flow<PagingData<Article>>{
//        return articleRepository.getEverythingNews(sources, country)
//    }
//}
