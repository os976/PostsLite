package com.example.postslite.domain.usecase

import com.example.postslite.domain.repository.PostsRepository
import javax.inject.Inject

class DeleteSavedUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(ids: List<Int>) {
        repository.deleteSaved(ids)
    }
}
