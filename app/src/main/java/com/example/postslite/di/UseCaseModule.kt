package com.example.postslite.di

import com.example.postslite.domain.repository.PostsRepository
import com.example.postslite.domain.usecase.AddRecentOpenedUseCase
import com.example.postslite.domain.usecase.ClearAllSavedUseCase
import com.example.postslite.domain.usecase.ClearRecentUseCase
import com.example.postslite.domain.usecase.DeleteSavedUseCase
import com.example.postslite.domain.usecase.ObservePostsUseCase
import com.example.postslite.domain.usecase.ObserveRecentUseCase
import com.example.postslite.domain.usecase.ObserveSavedUseCase
import com.example.postslite.domain.usecase.RestoreSavedUseCase
import com.example.postslite.domain.usecase.ToggleSaveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideObservePostsUseCase(repository: PostsRepository) =
        ObservePostsUseCase(repository)

    @Provides
    fun provideObserveSavedUseCase(repository: PostsRepository) =
        ObserveSavedUseCase(repository)

    @Provides
    fun provideObserveRecentUseCase(repository: PostsRepository) =
        ObserveRecentUseCase(repository)

    @Provides
    fun provideToggleSaveUseCase(repository: PostsRepository) =
        ToggleSaveUseCase(repository)

    @Provides
    fun provideDeleteSavedUseCase(repository: PostsRepository) =
        DeleteSavedUseCase(repository)

    @Provides
    fun provideClearAllSavedUseCase(repository: PostsRepository) =
        ClearAllSavedUseCase(repository)

    @Provides
    fun provideAddRecentOpenedUseCase(repository: PostsRepository) =
        AddRecentOpenedUseCase(repository)

    @Provides
    fun provideRestoreSavedUseCase(repository: PostsRepository) =
        RestoreSavedUseCase(repository)

    @Provides
    fun provideClearRecentUseCase(repository: PostsRepository) =
        ClearRecentUseCase(repository)
}
