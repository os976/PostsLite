package com.example.postslite.data.source;

import com.example.postslite.data.remote.PostDto;
import com.example.postslite.data.remote.PostsApi;
import com.example.postslite.di.IoDispatcher;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/postslite/data/source/PostsRemoteDataSourceImpl;", "Lcom/example/postslite/data/source/PostsRemoteDataSource;", "api", "Lcom/example/postslite/data/remote/PostsApi;", "io", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/example/postslite/data/remote/PostsApi;Lkotlinx/coroutines/CoroutineDispatcher;)V", "observePostsOnce", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/postslite/data/remote/PostDto;", "data_debug"})
public final class PostsRemoteDataSourceImpl implements com.example.postslite.data.source.PostsRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.remote.PostsApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher io = null;
    
    @javax.inject.Inject()
    public PostsRemoteDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.remote.PostsApi api, @com.example.postslite.di.IoDispatcher()
    @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher io) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.postslite.data.remote.PostDto>> observePostsOnce() {
        return null;
    }
}