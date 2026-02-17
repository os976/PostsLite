package com.example.postslite.data.repository;

import com.example.postslite.data.local.PostDao;
import com.example.postslite.data.local.RecentDao;
import com.example.postslite.data.remote.PostsApi;
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
    "KotlinInternalInJava"
})
public final class PostsRepositoryImpl_Factory implements Factory<PostsRepositoryImpl> {
  private final Provider<PostsApi> apiProvider;

  private final Provider<PostDao> postDaoProvider;

  private final Provider<RecentDao> recentDaoProvider;

  public PostsRepositoryImpl_Factory(Provider<PostsApi> apiProvider,
      Provider<PostDao> postDaoProvider, Provider<RecentDao> recentDaoProvider) {
    this.apiProvider = apiProvider;
    this.postDaoProvider = postDaoProvider;
    this.recentDaoProvider = recentDaoProvider;
  }

  @Override
  public PostsRepositoryImpl get() {
    return newInstance(apiProvider.get(), postDaoProvider.get(), recentDaoProvider.get());
  }

  public static PostsRepositoryImpl_Factory create(Provider<PostsApi> apiProvider,
      Provider<PostDao> postDaoProvider, Provider<RecentDao> recentDaoProvider) {
    return new PostsRepositoryImpl_Factory(apiProvider, postDaoProvider, recentDaoProvider);
  }

  public static PostsRepositoryImpl newInstance(PostsApi api, PostDao postDao,
      RecentDao recentDao) {
    return new PostsRepositoryImpl(api, postDao, recentDao);
  }
}
