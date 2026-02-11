package com.example.postslite.di

import com.example.postslite.data.repository.PostsRepositoryImpl
import com.example.postslite.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostsRepository(impl: PostsRepositoryImpl): PostsRepository
}
