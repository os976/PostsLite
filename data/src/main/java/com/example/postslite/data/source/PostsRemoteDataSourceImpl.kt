package com.example.postslite.data.source

import com.example.postslite.data.remote.PostDto
import com.example.postslite.data.remote.PostsApi
import com.example.postslite.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostsRemoteDataSourceImpl @Inject constructor(
    private val api: PostsApi,
    @IoDispatcher private val io: CoroutineDispatcher
) : PostsRemoteDataSource {

    override fun observePostsOnce(): Flow<List<PostDto>> =
        flow { emit(api.getPosts().posts) }
            .flowOn(io)
}
