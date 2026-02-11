package com.example.postslite.presentation.tabs.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    val state: StateFlow<UiState<List<Post>>> =
        repo.observeRecent()
            .map<List<Post>, UiState<List<Post>>> { list ->
                if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun toggleSave(post: Post) {
        viewModelScope.launch {
            repo.toggleSave(post)
        }
    }
}
