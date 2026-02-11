package com.example.postslite.domain.repository

import com.example.postslite.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    fun observePosts(query: Flow<String>): Flow<List<Post>>

    fun observeSaved(): Flow<List<Post>>

    fun observeRecent(): Flow<List<Post>>

    suspend fun toggleSave(post: Post)

    suspend fun deleteSaved(ids: List<Int>)

    suspend fun clearAllSaved()

    suspend fun addRecentOpened(post: Post)

    suspend fun restoreSaved(posts: List<Post>)

    suspend fun clearRecent()
}
