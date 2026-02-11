package com.example.postslite.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    private val _events = MutableSharedFlow<SettingsEvent>()
    val events: SharedFlow<SettingsEvent> = _events

    fun clearAllSaved() {
        viewModelScope.launch {
            runCatching { repo.clearAllSaved() }
                .onSuccess { _events.emit(SettingsEvent.SavedCleared) }
                .onFailure { _events.emit(SettingsEvent.Error(it.message ?: "Failed to clear saved")) }
        }
    }

    fun clearRecent() {
        viewModelScope.launch {
            runCatching { repo.clearRecent() }
                .onSuccess { _events.emit(SettingsEvent.RecentCleared) }
                .onFailure { _events.emit(SettingsEvent.Error(it.message ?: "Failed to clear recent")) }
        }
    }
}

sealed class SettingsEvent {
    data object SavedCleared : SettingsEvent()
    data object RecentCleared : SettingsEvent()
    data class Error(val message: String) : SettingsEvent()
}
