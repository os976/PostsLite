package com.example.postslite.domain.model

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val isSaved: Boolean
)
