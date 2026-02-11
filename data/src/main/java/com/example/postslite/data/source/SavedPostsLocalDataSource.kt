package com.example.postslite.data.source

import com.example.postslite.data.local.PostEntity
import kotlinx.coroutines.flow.Flow

interface SavedPostsLocalDataSource {
    fun observeSaved(): Flow<List<PostEntity>>
    suspend fun upsert(entity: PostEntity)
    suspend fun deleteById(id: Int)
    suspend fun deleteByIds(ids: List<Int>)
    suspend fun deleteAll()
}
