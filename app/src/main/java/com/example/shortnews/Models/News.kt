package com.example.shortnews.Models

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)