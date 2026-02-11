package com.example.postslite.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)
