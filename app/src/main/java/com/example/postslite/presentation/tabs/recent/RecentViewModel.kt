package com.example.postslite.presentation.tabs.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.ObserveRecentUseCase
import com.example.postslite.domain.usecase.ToggleSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    observeRecentUseCase: ObserveRecentUseCase,
    private val toggleSaveUseCase: ToggleSaveUseCase
) : ViewModel() {

    val recentPosts =
        observeRecentUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun toggleSave(post: Post) {
        viewModelScope.launch {
            toggleSaveUseCase(post)
        }
    }
}
