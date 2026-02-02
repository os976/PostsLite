package com.example.postslite.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    private val query = MutableStateFlow("")

    val state: StateFlow<UiState<List<Post>>> =
        repo.observePosts(query)
            .map< List<Post>, UiState<List<Post>> > { list ->
                if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun onSearchChanged(text: String) {
        query.value = text
    }

    fun onToggleSave(post: Post) {
        viewModelScope.launch { repo.toggleSave(post) }
    }
}
