package com.example.postslite.data.source;

import com.example.postslite.data.local.PostDao;
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
    "KotlinInternalInJava"
})
public final class SavedPostsLocalDataSourceImpl_Factory implements Factory<SavedPostsLocalDataSourceImpl> {
  private final Provider<PostDao> daoProvider;

  private final Provider<CoroutineDispatcher> ioProvider;

  public SavedPostsLocalDataSourceImpl_Factory(Provider<PostDao> daoProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    this.daoProvider = daoProvider;
    this.ioProvider = ioProvider;
  }

  @Override
  public SavedPostsLocalDataSourceImpl get() {
    return newInstance(daoProvider.get(), ioProvider.get());
  }

  public static SavedPostsLocalDataSourceImpl_Factory create(Provider<PostDao> daoProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    return new SavedPostsLocalDataSourceImpl_Factory(daoProvider, ioProvider);
  }

  public static SavedPostsLocalDataSourceImpl newInstance(PostDao dao, CoroutineDispatcher io) {
    return new SavedPostsLocalDataSourceImpl(dao, io);
  }
}
