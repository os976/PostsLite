package com.example.postslite.data.source;

import com.example.postslite.data.remote.PostsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata("com.example.postslite.di.IoDispatcher")
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
public final class PostsRemoteDataSourceImpl_Factory implements Factory<PostsRemoteDataSourceImpl> {
  private final Provider<PostsApi> apiProvider;

  private final Provider<CoroutineDispatcher> ioProvider;

  public PostsRemoteDataSourceImpl_Factory(Provider<PostsApi> apiProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    this.apiProvider = apiProvider;
    this.ioProvider = ioProvider;
  }

  @Override
  public PostsRemoteDataSourceImpl get() {
    return newInstance(apiProvider.get(), ioProvider.get());
  }

  public static PostsRemoteDataSourceImpl_Factory create(Provider<PostsApi> apiProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    return new PostsRemoteDataSourceImpl_Factory(apiProvider, ioProvider);
  }

  public static PostsRemoteDataSourceImpl newInstance(PostsApi api, CoroutineDispatcher io) {
    return new PostsRemoteDataSourceImpl(api, io);
  }
}
