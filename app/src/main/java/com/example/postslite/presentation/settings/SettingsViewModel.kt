package com.example.postslite.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    private val _events = MutableSharedFlow<SettingsEvent>(extraBufferCapacity = 1)
    val events: SharedFlow<SettingsEvent> = _events

    fun clearAllSaved() {
        viewModelScope.launch {
            val current = runCatching { repo.observeSaved().first() }.getOrElse { emptyList() }
            runCatching { repo.clearAllSaved() }
                .onSuccess { _events.tryEmit(SettingsEvent.DeletedSaved(current)) }
                .onFailure { _events.tryEmit(SettingsEvent.Error(it.message ?: "Failed to clear saved")) }
        }
    }

    fun clearRecent() {
        viewModelScope.launch {
            val current = runCatching { repo.observeRecent().first() }.getOrElse { emptyList() }
            runCatching { repo.clearRecent() }
                .onSuccess { _events.tryEmit(SettingsEvent.DeletedRecent(current)) }
                .onFailure { _events.tryEmit(SettingsEvent.Error(it.message ?: "Failed to clear recent")) }
        }
    }

    fun undoSaved(posts: List<Post>) {
        if (posts.isEmpty()) return
        viewModelScope.launch {
            repo.restoreSaved(posts)
        }
    }

    fun undoRecent(posts: List<Post>) {
        if (posts.isEmpty()) return
        viewModelScope.launch {
            posts.forEach { repo.addRecentOpened(it) }
        }
    }
}

sealed class SettingsEvent {
    data class DeletedSaved(val posts: List<Post>) : SettingsEvent()
    data class DeletedRecent(val posts: List<Post>) : SettingsEvent()
    data class Error(val message: String) : SettingsEvent()
}
