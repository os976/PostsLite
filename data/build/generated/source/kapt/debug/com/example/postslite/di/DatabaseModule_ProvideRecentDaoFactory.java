package com.example.postslite.di;

import com.example.postslite.data.local.AppDatabase;
import com.example.postslite.data.local.RecentDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideRecentDaoFactory implements Factory<RecentDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideRecentDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RecentDao get() {
    return provideRecentDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideRecentDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideRecentDaoFactory(dbProvider);
  }

  public static RecentDao provideRecentDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRecentDao(db));
  }
}
