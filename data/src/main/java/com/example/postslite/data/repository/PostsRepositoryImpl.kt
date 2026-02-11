package com.example.postslite.data.repository

import com.example.postslite.data.local.PostDao
import com.example.postslite.data.local.RecentDao
import com.example.postslite.data.local.RecentEntity
import com.example.postslite.data.mapper.toDomain
import com.example.postslite.data.mapper.toEntity
import com.example.postslite.data.remote.PostDto
import com.example.postslite.data.remote.PostsApi
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val api: PostsApi,
    private val postDao: PostDao,
    private val recentDao: RecentDao
) : PostsRepository {

    private val remoteOnceFlow: Flow<List<PostDto>> = flow {
        val response = withContext(Dispatchers.IO) { api.getPosts() }
        emit(response.posts)
    }

    override fun observePosts(query: Flow<String>): Flow<List<Post>> {
        val savedIdsFlow = postDao.observeSaved().map { list -> list.map { it.id }.toSet() }
        val safeQueryFlow = query.debounce(250).distinctUntilChanged()

        return combine(remoteOnceFlow, savedIdsFlow, safeQueryFlow) { remote, savedIds, q ->
            val mapped = remote.map { dto -> dto.toDomain(isSaved = savedIds.contains(dto.id)) }
            val trimmed = q.trim()
            if (trimmed.isEmpty()) mapped
            else mapped.filter {
                it.title.contains(trimmed, ignoreCase = true) ||
                        it.body.contains(trimmed, ignoreCase = true)
            }
        }
    }

    override fun observeSaved(): Flow<List<Post>> {
        return postDao.observeSaved().map { list ->
            list.map { Post(it.id, it.title, it.body, true) }
        }
    }

    override fun observeRecent(): Flow<List<Post>> {
        val savedIdsFlow = postDao.observeSaved().map { list -> list.map { it.id }.toSet() }
        val recentFlow = recentDao.observeRecent()

        return combine(recentFlow, savedIdsFlow) { recents, savedIds ->
            recents.map {
                Post(
                    id = it.id,
                    title = it.title,
                    body = it.body,
                    isSaved = savedIds.contains(it.id)
                )
            }
        }
    }

    override suspend fun toggleSave(post: Post) {
        withContext(Dispatchers.IO) {
            if (post.isSaved) postDao.deleteById(post.id)
            else postDao.upsert(post.copy(isSaved = true).toEntity())
        }
    }

    override suspend fun deleteSaved(ids: List<Int>) {
        if (ids.isEmpty()) return
        withContext(Dispatchers.IO) {
            postDao.deleteByIds(ids)
        }
    }

    override suspend fun clearAllSaved() {
        withContext(Dispatchers.IO) {
            postDao.deleteAll()
        }
    }

    override suspend fun addRecentOpened(post: Post) {
        withContext(Dispatchers.IO) {
            recentDao.upsert(
                RecentEntity(
                    id = post.id,
                    title = post.title,
                    body = post.body,
                    openedAt = System.currentTimeMillis()
                )
            )
        }
    }

    override suspend fun restoreSaved(posts: List<Post>) {
        if (posts.isEmpty()) return
        withContext(Dispatchers.IO) {
            posts.forEach { postDao.upsert(it.copy(isSaved = true).toEntity()) }
        }
    }

    override suspend fun clearRecent() {
        withContext(Dispatchers.IO) {
            recentDao.clearAll()
        }
    }
}
