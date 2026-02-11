package com.example.postslite.data.source

import com.example.postslite.data.local.RecentDao
import com.example.postslite.data.local.RecentEntity
import com.example.postslite.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentLocalDataSourceImpl @Inject constructor(
    private val dao: RecentDao,
    @IoDispatcher private val io: CoroutineDispatcher
) : RecentLocalDataSource {

    override fun observeRecent(): Flow<List<RecentEntity>> = dao.observeRecent().flowOn(io)

    override suspend fun upsert(entity: RecentEntity) {
        withContext(io) { dao.upsert(entity) }
    }

    override suspend fun clearAll() {
        withContext(io) { dao.clearAll() }
    }
}
