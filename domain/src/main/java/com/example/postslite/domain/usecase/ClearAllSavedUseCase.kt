package com.example.postslite.domain.usecase

import com.example.postslite.domain.repository.PostsRepository

class ClearAllSavedUseCase(
    private val repository: PostsRepository
) {
    suspend operator fun invoke() {
        repository.clearAllSaved()
    }
}
