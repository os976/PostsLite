package com.example.postslite.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.AddRecentUseCase
import com.example.postslite.domain.usecase.ObservePostsUseCase
import com.example.postslite.domain.usecase.ToggleSaveUseCase
import com.example.postslite.presentation.common.Event
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val observePostsUseCase: ObservePostsUseCase,
    private val toggleSaveUseCase: ToggleSaveUseCase,
    private val addRecentUseCase: AddRecentUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val _state = MutableLiveData<UiState<List<Post>>>(UiState.Loading)
    val state: LiveData<UiState<List<Post>>> = _state

    private var latestList: List<Post> = emptyList()

    private val _openDetails = MutableLiveData<Event<Post>>()
    val openDetails: LiveData<Event<Post>> = _openDetails

    init {
        viewModelScope.launch {
            observePostsUseCase(query)
                .map { list ->
                    latestList = list
                    if (list.isEmpty()) UiState.Empty else UiState.Success(list)
                }
                .onStart { emit(UiState.Loading) }
                .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
                .collectLatest { ui ->
                    _state.value = ui
                }
        }
    }

    fun onSearchChanged(text: String) {
        query.value = text
    }

    fun onPostClicked(post: Post) {
        viewModelScope.launch {
            addRecentUseCase(post)
            _openDetails.value = Event(post)
        }
    }

    fun onToggleSave(post: Post) {
        val current = latestList
        if (current.isNotEmpty()) {
            val updated = current.map { p ->
                if (p.id == post.id) p.copy(isSaved = !p.isSaved) else p
            }
            latestList = updated
            _state.value = if (updated.isEmpty()) UiState.Empty else UiState.Success(updated)
        }

        viewModelScope.launch {
            toggleSaveUseCase(post)
        }
    }
}
