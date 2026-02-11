package com.example.postslite.data.source;

import com.example.postslite.data.local.RecentDao;
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
public final class RecentLocalDataSourceImpl_Factory implements Factory<RecentLocalDataSourceImpl> {
  private final Provider<RecentDao> daoProvider;

  private final Provider<CoroutineDispatcher> ioProvider;

  public RecentLocalDataSourceImpl_Factory(Provider<RecentDao> daoProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    this.daoProvider = daoProvider;
    this.ioProvider = ioProvider;
  }

  @Override
  public RecentLocalDataSourceImpl get() {
    return newInstance(daoProvider.get(), ioProvider.get());
  }

  public static RecentLocalDataSourceImpl_Factory create(Provider<RecentDao> daoProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    return new RecentLocalDataSourceImpl_Factory(daoProvider, ioProvider);
  }

  public static RecentLocalDataSourceImpl newInstance(RecentDao dao, CoroutineDispatcher io) {
    return new RecentLocalDataSourceImpl(dao, io);
  }
}
