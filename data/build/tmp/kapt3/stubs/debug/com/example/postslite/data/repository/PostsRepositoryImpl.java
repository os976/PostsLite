package com.example.postslite.data.repository;

import com.example.postslite.data.local.RecentEntity;
import com.example.postslite.data.source.PostsRemoteDataSource;
import com.example.postslite.data.source.RecentLocalDataSource;
import com.example.postslite.data.source.SavedPostsLocalDataSource;
import com.example.postslite.di.AppClock;
import com.example.postslite.domain.model.Post;
import com.example.postslite.domain.repository.PostsRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0013\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00150\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0019H\u0016J\u0014\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00150\u0019H\u0016J\u0014\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00150\u0019H\u0016J\u001c\u0010\u001e\u001a\u00020\f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/postslite/data/repository/PostsRepositoryImpl;", "Lcom/example/postslite/domain/repository/PostsRepository;", "remote", "Lcom/example/postslite/data/source/PostsRemoteDataSource;", "savedLocal", "Lcom/example/postslite/data/source/SavedPostsLocalDataSource;", "recentLocal", "Lcom/example/postslite/data/source/RecentLocalDataSource;", "clock", "Lcom/example/postslite/di/AppClock;", "(Lcom/example/postslite/data/source/PostsRemoteDataSource;Lcom/example/postslite/data/source/SavedPostsLocalDataSource;Lcom/example/postslite/data/source/RecentLocalDataSource;Lcom/example/postslite/di/AppClock;)V", "addRecentOpened", "", "post", "Lcom/example/postslite/domain/model/Post;", "(Lcom/example/postslite/domain/model/Post;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAllSaved", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearRecent", "deleteSaved", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observePosts", "Lkotlinx/coroutines/flow/Flow;", "query", "", "observeRecent", "observeSaved", "restoreSaved", "posts", "toggleSave", "data_debug"})
public final class PostsRepositoryImpl implements com.example.postslite.domain.repository.PostsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.source.PostsRemoteDataSource remote = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.source.SavedPostsLocalDataSource savedLocal = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.source.RecentLocalDataSource recentLocal = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.di.AppClock clock = null;
    
    @javax.inject.Inject()
    public PostsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.PostsRemoteDataSource remote, @org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.SavedPostsLocalDataSource savedLocal, @org.jetbrains.annotations.NotNull()
    com.example.postslite.data.source.RecentLocalDataSource recentLocal, @org.jetbrains.annotations.NotNull()
    com.example.postslite.di.AppClock clock) {
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