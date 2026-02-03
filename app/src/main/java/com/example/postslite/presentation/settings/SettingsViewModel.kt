package com.example.postslite.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    fun clearAllSaved() {
        viewModelScope.launch { repo.clearAllSaved() }
    }

    fun clearRecent() {
        viewModelScope.launch { repo.clearRecent() }
    }
}
