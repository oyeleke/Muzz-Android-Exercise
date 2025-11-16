package com.example.muzzandroidexercise.utils

import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

class TimeUtils {

    fun now() :  Long {
        return System.currentTimeMillis()
    }

    fun diffMillis(startTime: Long, endTime: Long = now()) : Long {
        return endTime - startTime
    }

    fun formatDuration(millis: Long): Time {
        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60

        return Time(hours.toInt(), minutes.toInt(), seconds.toInt())
    }

    fun getHourOfDay(ms: Long): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = ms
        return cal.get(Calendar.HOUR_OF_DAY)
    }

    fun getMinuteOfHour(ms: Long): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = ms
        return cal.get(Calendar.MINUTE)
    }

    fun getDayOfWeek(ms: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = ms
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) ?: ""
    }
}

data class Time(val hour: Int, val minute: Int, val second: Int)