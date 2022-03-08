package com.lyhv.mvvm.core.api.response

import com.squareup.moshi.Json

data class NewsResponse internal constructor(
    @field:Json(name = "author")
    val author: String,
    @field:Json(name = "content")
    val content: String,
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "time")
    val time: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "url")
    val url: String
)