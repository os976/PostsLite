package com.example.postslite.data.mapper

import com.example.postslite.data.local.PostEntity
import com.example.postslite.data.remote.PostDto
import com.example.postslite.domain.model.Post

fun PostDto.toDomain(isSaved: Boolean): Post =
    Post(id = id, title = title, body = body, isSaved = isSaved)

fun Post.toEntity(): PostEntity =
    PostEntity(id = id, title = title, body = body)
