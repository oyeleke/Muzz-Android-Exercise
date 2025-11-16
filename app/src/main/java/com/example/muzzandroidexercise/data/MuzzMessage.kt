package com.example.muzzandroidexercise.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.muzzandroidexercise.utils.Time
import com.example.muzzandroidexercise.utils.TimeUtils

@Entity(tableName = "messages")
data class MuzzMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val senderId: Int = -1,
    val content: String,
    val durationToLastMessage: Long,
    val timestamp: Long
) {

    val getDurationToLastMessage: Time
        get() = TimeUtils().formatDuration(durationToLastMessage)

    val isFromMe: Boolean
        get() = senderId == -1

    val dayOfMessage: String
        get() = TimeUtils().getDayOfWeek(timestamp)

    val hourAndMinuteOfMessage: String
        get() = "${TimeUtils().getHourOfDay(timestamp)}:${TimeUtils().getMinuteOfHour(timestamp)}"
}
