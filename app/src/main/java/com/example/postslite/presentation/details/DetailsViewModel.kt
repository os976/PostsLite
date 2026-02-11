package com.example.postslite.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.ToggleSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val toggleSave: ToggleSaveUseCase
) : ViewModel() {

    fun toggle(post: Post) {
        viewModelScope.launch { toggleSave(post) }
    }
}
