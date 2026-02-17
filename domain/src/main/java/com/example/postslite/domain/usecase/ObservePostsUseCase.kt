package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke(query: Flow<String>): Flow<List<Post>> {
        return repository.observePosts(query)
    }
}
