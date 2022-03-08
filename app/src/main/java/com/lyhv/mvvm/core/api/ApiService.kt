package com.lyhv.mvvm.core.api

import com.lyhv.mvvm.BuildConfig
import com.lyhv.mvvm.core.api.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val ENDPOINT = BuildConfig.BASE_API_URL
    }

    @GET("news")
    suspend fun getNews(@Query("category") categoryName: String = ""): Response<ItemsResponse<NewsResponse>>
}