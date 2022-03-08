package com.lyhv.mvvm.core.api

import com.squareup.moshi.Json

data class ResultResponse<T>(
    @field:Json(name = "data") val data: T?
)
