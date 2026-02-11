package com.example.postslite.di

import com.example.postslite.data.local.PostDao
import com.example.postslite.data.local.RecentDao
import com.example.postslite.data.remote.PostsApi
import com.example.postslite.data.repository.PostsRepositoryImpl
import com.example.postslite.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostsRepository(
        api: PostsApi,
        postDao: PostDao,
        recentDao: RecentDao
    ): PostsRepository {
        return PostsRepositoryImpl(api, postDao, recentDao)
    }
}
