package com.example.postslite.data.source

import com.example.postslite.data.remote.PostDto
import kotlinx.coroutines.flow.Flow

interface PostsRemoteDataSource {
    fun observePostsOnce(): Flow<List<PostDto>>
}
