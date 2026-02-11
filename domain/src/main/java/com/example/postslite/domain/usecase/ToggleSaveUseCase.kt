package com.example.postslite.domain.usecase

import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository

class ToggleSaveUseCase(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(post: Post) {
        repository.toggleSave(post)
    }
}
