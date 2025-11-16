package com.example.muzzandroidexercise.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MuzzMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val senderId: Int = -1,
    val content: String,
    val durationToLastMessage: Long,
    val timestamp: Long
)
