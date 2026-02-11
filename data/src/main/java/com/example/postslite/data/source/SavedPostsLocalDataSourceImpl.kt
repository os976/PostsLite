package com.example.postslite.data.source

import com.example.postslite.data.local.PostDao
import com.example.postslite.data.local.PostEntity
import com.example.postslite.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedPostsLocalDataSourceImpl @Inject constructor(
    private val dao: PostDao,
    @IoDispatcher private val io: CoroutineDispatcher
) : SavedPostsLocalDataSource {

    override fun observeSaved(): Flow<List<PostEntity>> = dao.observeSaved().flowOn(io)

    override suspend fun upsert(entity: PostEntity) {
        withContext(io) { dao.upsert(entity) }
    }

    override suspend fun deleteById(id: Int) {
        withContext(io) { dao.deleteById(id) }
    }

    override suspend fun deleteByIds(ids: List<Int>) {
        withContext(io) { dao.deleteByIds(ids) }
    }

    override suspend fun deleteAll() {
        withContext(io) { dao.deleteAll() }
    }
}
