package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import javax.inject.Inject

class RestoreSavedUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(posts: List<Post>) {
        repository.restoreSaved(posts)
    }
}
