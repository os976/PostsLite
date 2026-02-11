package com.example.postslite.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.DeleteSavedUseCase
import com.example.postslite.domain.usecase.ObserveSavedUseCase
import com.example.postslite.domain.usecase.RestoreSavedUseCase
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
class SavedViewModel @Inject constructor(
    private val observeSaved: ObserveSavedUseCase,
    private val deleteSaved: DeleteSavedUseCase,
    private val restoreSaved: RestoreSavedUseCase
) : ViewModel() {

    val state: StateFlow<UiState<List<Post>>> =
        observeSaved()
            .map<List<Post>, UiState<List<Post>>> { list ->
                if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun deleteSelected(ids: List<Int>) {
        viewModelScope.launch { deleteSaved(ids) }
    }

    fun restore(posts: List<Post>) {
        viewModelScope.launch { restoreSaved(posts) }
    }
}
