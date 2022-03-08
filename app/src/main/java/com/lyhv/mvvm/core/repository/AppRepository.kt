package com.lyhv.mvvm.core.repository

import com.lyhv.mvvm.common.utils.SPUtils
import com.lyhv.mvvm.core.api.ItemsResponse
import com.lyhv.mvvm.core.api.response.NewsResponse
import com.lyhv.mvvm.core.data.Result
import com.lyhv.mvvm.core.data.resultFlowNoCache
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val spUtils: SPUtils
) {
    fun fetchNews(categoryName: String): Flow<Result<ItemsResponse<NewsResponse>>> {
        return resultFlowNoCache(
            networkCall = {
                remoteDataSource.fetchNews(categoryName = categoryName)
            }
        )
    }
}