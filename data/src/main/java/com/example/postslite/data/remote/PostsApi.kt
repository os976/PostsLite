package com.example.postslite.data.remote

import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): PostsResponseDto
}
