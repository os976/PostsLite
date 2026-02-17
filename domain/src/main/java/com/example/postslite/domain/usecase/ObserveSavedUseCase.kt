package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSavedUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke(): Flow<List<Post>> {
        return repository.observeSaved()
    }
}
