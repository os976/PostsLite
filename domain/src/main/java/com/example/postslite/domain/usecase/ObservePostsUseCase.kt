package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow

class ObservePostsUseCase(
    private val repository: PostsRepository
) {
    operator fun invoke(query: Flow<String>): Flow<List<Post>> =
        repository.observePosts(query)
}
