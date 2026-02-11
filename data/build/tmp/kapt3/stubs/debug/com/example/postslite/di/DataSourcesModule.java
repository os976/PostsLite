package com.example.postslite.di;

import com.example.postslite.data.source.PostsRemoteDataSource;
import com.example.postslite.data.source.PostsRemoteDataSourceImpl;
import com.example.postslite.data.source.RecentLocalDataSource;
import com.example.postslite.data.source.RecentLocalDataSourceImpl;
import com.example.postslite.data.source.SavedPostsLocalDataSource;
import com.example.postslite.data.source.SavedPostsLocalDataSourceImpl;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'\u00a8\u0006\r"}, d2 = {"Lcom/example/postslite/di/DataSourcesModule;", "", "()V", "bindRecentLocalDataSource", "Lcom/example/postslite/data/source/RecentLocalDataSource;", "impl", "Lcom/example/postslite/data/source/RecentLocalDataSourceImpl;", "bindRemoteDataSource", "Lcom/example/postslite/data/source/PostsRemoteDataSource;", "Lcom/example/postslite/data/source/PostsRemoteDataSourceImpl;", "bindSavedLocalDataSource", "Lcom/example/postslite/data/source/SavedPostsLocalDataSource;", "Lcom/example/postslite/data/source/SavedPostsLocalDataSourceImpl;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class DataSourcesModule {
    
    public DataSourcesModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.postslite.data.source.PostsRemoteDataSource bindRemoteDataSource(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.PostsRemoteDataSourceImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.postslite.data.source.SavedPostsLocalDataSource bindSavedLocalDataSource(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.SavedPostsLocalDataSourceImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.postslite.data.source.RecentLocalDataSource bindRecentLocalDataSource(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.RecentLocalDataSourceImpl impl);
}