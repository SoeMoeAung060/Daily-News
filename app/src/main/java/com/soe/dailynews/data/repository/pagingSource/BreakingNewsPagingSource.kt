package com.soe.dailynews.data.repository.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.data.repository.remoteMediator.RemoteMediator
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.util.PER_PAGE_SIZE

class BreakingNewsPagingSource(
    private val newsApi: NewsApi,
    private val country: String,
    private val newsDatabase: NewsDatabase
) : PagingSource<Int, Article>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = newsApi.getBreakingNews(country = country, page = page, pageSize = PER_PAGE_SIZE)
            val endOfPaginationReached = (page * params.loadSize) >= response.totalResults
            if (response.articles.isNotEmpty()) {
                LoadResult.Page(
                    data = response.articles,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1
                )
            }else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}