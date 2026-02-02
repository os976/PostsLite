package com.example.postslite.data.repository

import com.example.postslite.data.local.PostDao
import com.example.postslite.data.mapper.toDomain
import com.example.postslite.data.mapper.toEntity
import com.example.postslite.data.remote.PostDto
import com.example.postslite.data.remote.PostsApi
import com.example.postslite.domain.model.Post
import com.example.postslite.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class PostsRepositoryImpl(
    private val api: PostsApi,
    private val dao: PostDao
) : PostsRepository {

    // ✅ Remote fetch مرة واحدة (بسيط جدًا)
    private val remoteOnceFlow: Flow<List<PostDto>> = flow {
        val response = withContext(Dispatchers.IO) { api.getPosts() }
        emit(response.posts)
    }

    override fun observePosts(query: Flow<String>): Flow<List<Post>> {
        val savedIdsFlow = dao.observeSaved()
            .map { list -> list.map { it.id }.toSet() }

        val safeQueryFlow = query
            .debounce(250)
            .distinctUntilChanged()

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
        return dao.observeSaved().map { list ->
            list.map { Post(it.id, it.title, it.body, isSaved = true) }
        }
    }

    override suspend fun toggleSave(post: Post) {
        withContext(Dispatchers.IO) {
            if (post.isSaved) dao.deleteById(post.id)
            else dao.upsert(post.copy(isSaved = true).toEntity())
        }

    }
    override suspend fun deleteSaved(ids: List<Int>) {
        if (ids.isEmpty()) return
        dao.deleteByIds(ids)
    }

}
