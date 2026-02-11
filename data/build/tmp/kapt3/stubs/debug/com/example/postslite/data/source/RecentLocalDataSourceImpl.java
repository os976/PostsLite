package com.example.postslite.data.source;

import com.example.postslite.data.local.RecentDao;
import com.example.postslite.data.local.RecentEntity;
import com.example.postslite.di.IoDispatcher;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bH\u0016J\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/postslite/data/source/RecentLocalDataSourceImpl;", "Lcom/example/postslite/data/source/RecentLocalDataSource;", "dao", "Lcom/example/postslite/data/local/RecentDao;", "io", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/example/postslite/data/local/RecentDao;Lkotlinx/coroutines/CoroutineDispatcher;)V", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeRecent", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/postslite/data/local/RecentEntity;", "upsert", "entity", "(Lcom/example/postslite/data/local/RecentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class RecentLocalDataSourceImpl implements com.example.postslite.data.source.RecentLocalDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.example.postslite.data.local.RecentDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher io = null;
    
    @javax.inject.Inject()
    public RecentLocalDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.local.RecentDao dao, @com.example.postslite.di.IoDispatcher()
    @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher io) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.postslite.data.local.RecentEntity>> observeRecent() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.example.postslite.data.local.RecentEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}