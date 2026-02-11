package com.example.postslite.data.repository

import com.example.postslite.data.local.RecentEntity
import com.example.postslite.data.mapper.toDomain
import com.example.postslite.data.mapper.toEntity
import com.example.postslite.data.source.PostsRemoteDataSource
import com.example.postslite.data.source.RecentLocalDataSource
import com.example.postslite.data.source.SavedPostsLocalDataSource
import com.example.postslite.di.AppClock
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val remote: PostsRemoteDataSource,
    private val savedLocal: SavedPostsLocalDataSource,
    private val recentLocal: RecentLocalDataSource,
    private val clock: AppClock
) : PostsRepository {

    override fun observePosts(query: Flow<String>): Flow<List<Post>> {
        val savedIdsFlow = savedLocal.observeSaved()
            .map { entities -> entities.asSequence().map { it.id }.toSet() }

        val safeQueryFlow = query
            .debounce(250)
            .distinctUntilChanged()
            .map { it.trim() }

        return combine(remote.observePostsOnce(), savedIdsFlow, safeQueryFlow) { remoteDtos, savedIds, q ->
            val posts = remoteDtos.map { dto -> dto.toDomain(isSaved = savedIds.contains(dto.id)) }
            if (q.isEmpty()) posts
            else posts.filter { it.title.contains(q, ignoreCase = true) || it.body.contains(q, ignoreCase = true) }
        }
    }

    override fun observeSaved(): Flow<List<Post>> {
        return savedLocal.observeSaved()
            .map { entities -> entities.map { Post(it.id, it.title, it.body, true) } }
    }

    override fun observeRecent(): Flow<List<Post>> {
        val savedIdsFlow = savedLocal.observeSaved()
            .map { entities -> entities.asSequence().map { it.id }.toSet() }

        return combine(recentLocal.observeRecent(), savedIdsFlow) { recents, savedIds ->
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
        if (post.isSaved) savedLocal.deleteById(post.id)
        else savedLocal.upsert(post.copy(isSaved = true).toEntity())
    }

    override suspend fun deleteSaved(ids: List<Int>) {
        if (ids.isEmpty()) return
        savedLocal.deleteByIds(ids)
    }

    override suspend fun clearAllSaved() {
        savedLocal.deleteAll()
    }

    override suspend fun addRecentOpened(post: Post) {
        recentLocal.upsert(
            RecentEntity(
                id = post.id,
                title = post.title,
                body = post.body,
                openedAt = clock.now()
            )
        )
    }

    override suspend fun restoreSaved(posts: List<Post>) {
        if (posts.isEmpty()) return
        posts.forEach { savedLocal.upsert(it.copy(isSaved = true).toEntity()) }
    }

    override suspend fun clearRecent() {
        recentLocal.clearAll()
    }
}
