package com.lyhv.mvvm.core.api

import com.squareup.moshi.Json

data class ItemsResponse<T>(
    @field:Json(name = "data") val items: List<T> = emptyList()
)
