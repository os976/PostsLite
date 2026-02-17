package com.example.postslite.data.repository;

import com.example.postslite.data.local.PostDao;
import com.example.postslite.data.local.RecentDao;
import com.example.postslite.data.local.RecentEntity;
import com.example.postslite.data.remote.PostsApi;
import com.example.postslite.domain.model.Post;
import com.example.postslite.domain.repository.PostsRepository;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0011\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017H\u0016J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u0017H\u0016J\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u0017H\u0016J\u001c\u0010\u001c\u001a\u00020\n2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/postslite/data/repository/PostsRepositoryImpl;", "Lcom/example/postslite/domain/repository/PostsRepository;", "api", "Lcom/example/postslite/data/remote/PostsApi;", "postDao", "Lcom/example/postslite/data/local/PostDao;", "recentDao", "Lcom/example/postslite/data/local/RecentDao;", "(Lcom/example/postslite/data/remote/PostsApi;Lcom/example/postslite/data/local/PostDao;Lcom/example/postslite/data/local/RecentDao;)V", "addRecentOpened", "", "post", "Lcom/example/postslite/domain/model/Post;", "(Lcom/example/postslite/domain/model/Post;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAllSaved", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearRecent", "deleteSaved", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observePosts", "Lkotlinx/coroutines/flow/Flow;", "query", "", "observeRecent", "observeSaved", "restoreSaved", "posts", "toggleSave", "data_debug"})
public final class PostsRepositoryImpl implements com.example.postslite.domain.repository.PostsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.remote.PostsApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.local.PostDao postDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.local.RecentDao recentDao = null;
    
    @javax.inject.Inject()
    public PostsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.remote.PostsApi api, @org.jetbrains.annotations.NotNull()
    com.example.postslite.data.local.PostDao postDao, @org.jetbrains.annotations.NotNull()
    com.example.postslite.data.local.RecentDao recentDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.postslite.domain.model.Post>> observePosts(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.Flow<java.lang.String> query) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.postslite.domain.model.Post>> observeSaved() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.postslite.domain.model.Post>> observeRecent() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object toggleSave(@org.jetbrains.annotations.NotNull()
    com.example.postslite.domain.model.Post post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteSaved(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAllSaved(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addRecentOpened(@org.jetbrains.annotations.NotNull()
    com.example.postslite.domain.model.Post post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object restoreSaved(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.postslite.domain.model.Post> posts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearRecent(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}