package com.example.postslite.presentation.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.usecase.AddRecentUseCase
import com.example.postslite.domain.usecase.DeleteSavedUseCase
import com.example.postslite.domain.usecase.ObserveSavedUseCase
import com.example.postslite.domain.usecase.RestoreSavedUseCase
import com.example.postslite.presentation.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    observeSavedUseCase: ObserveSavedUseCase,
    private val deleteSavedUseCase: DeleteSavedUseCase,
    private val restoreSavedUseCase: RestoreSavedUseCase,
    private val addRecentUseCase: AddRecentUseCase
) : ViewModel() {

    val savedPosts = observeSavedUseCase().asLiveData()

    private val _openDetails = MutableLiveData<Event<Post>>()
    val openDetails: LiveData<Event<Post>> = _openDetails

    private val _deleteEvent = MutableSharedFlow<List<Post>>(extraBufferCapacity = 1)
    val deleteEvent: SharedFlow<List<Post>> = _deleteEvent

    fun onPostClicked(post: Post) {
        viewModelScope.launch {
            addRecentUseCase(post)
            _openDetails.value = Event(post)
        }
    }

    fun deletePosts(posts: List<Post>) {
        if (posts.isEmpty()) return
        viewModelScope.launch {
            deleteSavedUseCase(posts.map { it.id })
            _deleteEvent.tryEmit(posts)
        }
    }

    fun restore(posts: List<Post>) {
        if (posts.isEmpty()) return
        viewModelScope.launch {
            restoreSavedUseCase(posts)
        }
    }
}
