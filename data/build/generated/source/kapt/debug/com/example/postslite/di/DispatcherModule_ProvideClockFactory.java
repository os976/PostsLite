package com.example.postslite.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DispatcherModule_ProvideClockFactory implements Factory<AppClock> {
  @Override
  public AppClock get() {
    return provideClock();
  }

  public static DispatcherModule_ProvideClockFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppClock provideClock() {
    return Preconditions.checkNotNullFromProvides(DispatcherModule.INSTANCE.provideClock());
  }

  private static final class InstanceHolder {
    private static final DispatcherModule_ProvideClockFactory INSTANCE = new DispatcherModule_ProvideClockFactory();
  }
}
