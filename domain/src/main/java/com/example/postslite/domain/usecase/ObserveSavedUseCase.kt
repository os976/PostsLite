package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow

class ObserveSavedUseCase(
    private val repository: PostsRepository
) {
    operator fun invoke(): Flow<List<Post>> = repository.observeSaved()
}
