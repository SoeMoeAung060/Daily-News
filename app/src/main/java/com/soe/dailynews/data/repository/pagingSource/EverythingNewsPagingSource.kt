//package com.soe.dailynews.data.repository.pagingSource
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.soe.dailynews.data.remote.api.NewsApi
//import com.soe.dailynews.domain.model.Article
//import com.soe.dailynews.util.PER_PAGE_SIZE
//
//
//class EverythingNewsPagingSource(
//    private val newsApi: NewsApi,
//    private val country: String,
//    private val source: String
//) : PagingSource<Int, Article>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        return try {
//            val page = params.key ?: 1
//            val response = newsApi.getNewsEverything(source = source, country = country, page = page, pageSize = PER_PAGE_SIZE)
//            val endOfPaginationReached = response.articles.isEmpty()
//            Log.d("EverythingNewsPagingSource", "Response: $response")
//            if (response.articles.isNotEmpty()) {
//                LoadResult.Page(
//                    data = response.articles,
//                    prevKey = if (page == 1) null else page - 1,
//                    nextKey = if (endOfPaginationReached) null else page + 1
//                )
//            }else {
//                LoadResult.Page(
//                    data = emptyList(),
//                    prevKey = null,
//                    nextKey = null
//                )
//            }
//
//        }catch (e : Exception){
//            LoadResult.Error(e)
//        }
//    }
//}