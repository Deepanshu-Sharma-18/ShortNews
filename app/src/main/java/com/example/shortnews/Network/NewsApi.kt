package com.example.shortnews.Network

import com.example.shortnews.Models.News
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")country : String,
        @Query("apikey")api : String = "04d9d6589724427aac5e9f7c87a644fd"
    ) : News
}