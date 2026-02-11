package com.example.postslite.di;

import com.example.postslite.data.local.PostDao;
import com.example.postslite.data.local.RecentDao;
import com.example.postslite.data.remote.PostsApi;
import com.example.postslite.domain.repository.PostsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_ProvidePostsRepositoryFactory implements Factory<PostsRepository> {
  private final Provider<PostsApi> apiProvider;

  private final Provider<PostDao> postDaoProvider;

  private final Provider<RecentDao> recentDaoProvider;

  public RepositoryModule_ProvidePostsRepositoryFactory(Provider<PostsApi> apiProvider,
      Provider<PostDao> postDaoProvider, Provider<RecentDao> recentDaoProvider) {
    this.apiProvider = apiProvider;
    this.postDaoProvider = postDaoProvider;
    this.recentDaoProvider = recentDaoProvider;
  }

  @Override
  public PostsRepository get() {
    return providePostsRepository(apiProvider.get(), postDaoProvider.get(), recentDaoProvider.get());
  }

  public static RepositoryModule_ProvidePostsRepositoryFactory create(
      Provider<PostsApi> apiProvider, Provider<PostDao> postDaoProvider,
      Provider<RecentDao> recentDaoProvider) {
    return new RepositoryModule_ProvidePostsRepositoryFactory(apiProvider, postDaoProvider, recentDaoProvider);
  }

  public static PostsRepository providePostsRepository(PostsApi api, PostDao postDao,
      RecentDao recentDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providePostsRepository(api, postDao, recentDao));
  }
}
