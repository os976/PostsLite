package com.example.postslite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM saved_posts ORDER BY id DESC")
    fun observeSaved(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PostEntity)

    @Query("DELETE FROM saved_posts WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM saved_posts WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Int>)

    @Query("DELETE FROM saved_posts")
    suspend fun deleteAll()
}
