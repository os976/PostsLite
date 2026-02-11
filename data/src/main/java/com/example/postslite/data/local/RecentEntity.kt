package com.example.postslite.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_opened")
data class RecentEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
    val openedAt: Long
)
