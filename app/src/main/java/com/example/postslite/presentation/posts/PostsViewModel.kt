package com.example.postslite.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.AddRecentOpenedUseCase
import com.example.postslite.domain.usecase.ObservePostsUseCase
import com.example.postslite.domain.usecase.ToggleSaveUseCase
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val observePosts: ObservePostsUseCase,
    private val toggleSave: ToggleSaveUseCase,
    private val addRecentOpened: AddRecentOpenedUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")

    val state: StateFlow<UiState<List<Post>>> =
        observePosts(query)
            .map<List<Post>, UiState<List<Post>>> { list ->
                if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun onSearchChanged(text: String) {
        query.value = text
    }

    fun onToggleSave(post: Post) {
        viewModelScope.launch { toggleSave(post) }
    }

    fun onOpened(post: Post) {
        viewModelScope.launch { addRecentOpened(post) }
    }
}
