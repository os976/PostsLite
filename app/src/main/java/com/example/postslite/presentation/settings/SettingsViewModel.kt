package com.example.postslite.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.usecase.ClearAllSavedUseCase
import com.example.postslite.domain.usecase.ClearRecentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val clearAllSaved: ClearAllSavedUseCase,
    private val clearRecent: ClearRecentUseCase
) : ViewModel() {

    fun clearAllSaved() {
        viewModelScope.launch { clearAllSaved() }
    }

    fun clearRecent() {
        viewModelScope.launch { clearRecent() }
    }
}
