package com.example.postslite.di

import android.content.Context
import androidx.room.Room
import com.example.postslite.data.local.AppDatabase
import com.example.postslite.data.local.PostDao
import com.example.postslite.data.local.RecentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "posts.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides
    fun provideRecentDao(db: AppDatabase): RecentDao = db.recentDao()
}
