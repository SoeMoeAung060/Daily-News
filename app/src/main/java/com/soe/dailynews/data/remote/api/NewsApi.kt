package com.soe.dailynews.data.remote.api

import com.soe.dailynews.data.remote.dto.NewsResponseDTO
import com.soe.dailynews.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query



interface NewsApi {




    @GET("top-headlines")
    suspend fun getBreakingNews(

        @Query("page") page : Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey : String = API_KEY

        ) : NewsResponseDTO



    @GET("everything")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponseDTO


    @GET("everything")
    suspend fun searchNews(
        @Query("q") search : String,
        @Query("sources") sources: String,
        @Query("page") page : Int,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponseDTO

}