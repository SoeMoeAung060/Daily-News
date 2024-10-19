package com.soe.dailynews.data.remote.api

import com.soe.dailynews.data.remote.dto.NewsResponseDTO
import com.soe.dailynews.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query



interface NewsApi {

    @GET("everything")
    suspend fun getNewsEverything(

        @Query("country") country : String,
        @Query("sources") source : String,
        @Query("page") page : Int,
        @Query("pageSize") pageSize :Int,
        @Query("apiKey") apiKey : String = API_KEY,

    ) : NewsResponseDTO



    @GET("top-headlines")
    suspend fun getBreakingNews(

        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize : Int,
        @Query("apiKey") apiKey: String = API_KEY

        ) : NewsResponseDTO


}