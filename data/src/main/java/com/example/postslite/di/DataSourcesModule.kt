package com.example.postslite.di

import com.example.postslite.data.source.PostsRemoteDataSource
import com.example.postslite.data.source.PostsRemoteDataSourceImpl
import com.example.postslite.data.source.RecentLocalDataSource
import com.example.postslite.data.source.RecentLocalDataSourceImpl
import com.example.postslite.data.source.SavedPostsLocalDataSource
import com.example.postslite.data.source.SavedPostsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: PostsRemoteDataSourceImpl): PostsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindSavedLocalDataSource(impl: SavedPostsLocalDataSourceImpl): SavedPostsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRecentLocalDataSource(impl: RecentLocalDataSourceImpl): RecentLocalDataSource
}
