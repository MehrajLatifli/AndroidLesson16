package com.example.news.models


import com.google.gson.annotations.SerializedName

data class NewsAPIResponse(
    @SerializedName("articles")
    val articles: List<Article>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)