package com.lyhv.mvvm.core.repository

import com.lyhv.mvvm.core.api.ApiService
import com.lyhv.mvvm.core.api.BaseDataSource
import com.lyhv.mvvm.core.api.ItemsResponse
import com.lyhv.mvvm.core.api.response.NewsResponse
import com.lyhv.mvvm.core.data.Result
import javax.inject.Inject

class RemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    BaseDataSource() {

    suspend fun fetchNews(categoryName: String): Result<ItemsResponse<NewsResponse>> {
        return getResult {
            apiService.getNews(categoryName)
        }
    }
}
