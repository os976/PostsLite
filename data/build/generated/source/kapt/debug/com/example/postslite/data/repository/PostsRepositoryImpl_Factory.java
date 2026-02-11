package com.example.postslite.data.repository;

import com.example.postslite.data.source.PostsRemoteDataSource;
import com.example.postslite.data.source.RecentLocalDataSource;
import com.example.postslite.data.source.SavedPostsLocalDataSource;
import com.example.postslite.di.AppClock;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class PostsRepositoryImpl_Factory implements Factory<PostsRepositoryImpl> {
  private final Provider<PostsRemoteDataSource> remoteProvider;

  private final Provider<SavedPostsLocalDataSource> savedLocalProvider;

  private final Provider<RecentLocalDataSource> recentLocalProvider;

  private final Provider<AppClock> clockProvider;

  public PostsRepositoryImpl_Factory(Provider<PostsRemoteDataSource> remoteProvider,
      Provider<SavedPostsLocalDataSource> savedLocalProvider,
      Provider<RecentLocalDataSource> recentLocalProvider, Provider<AppClock> clockProvider) {
    this.remoteProvider = remoteProvider;
    this.savedLocalProvider = savedLocalProvider;
    this.recentLocalProvider = recentLocalProvider;
    this.clockProvider = clockProvider;
  }

  @Override
  public PostsRepositoryImpl get() {
    return newInstance(remoteProvider.get(), savedLocalProvider.get(), recentLocalProvider.get(), clockProvider.get());
  }

  public static PostsRepositoryImpl_Factory create(Provider<PostsRemoteDataSource> remoteProvider,
      Provider<SavedPostsLocalDataSource> savedLocalProvider,
      Provider<RecentLocalDataSource> recentLocalProvider, Provider<AppClock> clockProvider) {
    return new PostsRepositoryImpl_Factory(remoteProvider, savedLocalProvider, recentLocalProvider, clockProvider);
  }

  public static PostsRepositoryImpl newInstance(PostsRemoteDataSource remote,
      SavedPostsLocalDataSource savedLocal, RecentLocalDataSource recentLocal, AppClock clock) {
    return new PostsRepositoryImpl(remote, savedLocal, recentLocal, clock);
  }
}
