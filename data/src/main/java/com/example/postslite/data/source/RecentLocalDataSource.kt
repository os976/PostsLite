package com.example.postslite.data.source

import com.example.postslite.data.local.RecentEntity
import kotlinx.coroutines.flow.Flow

interface RecentLocalDataSource {
    fun observeRecent(): Flow<List<RecentEntity>>
    suspend fun upsert(entity: RecentEntity)
    suspend fun clearAll()
}
