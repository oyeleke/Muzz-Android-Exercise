package com.example.muzzandroidexercise.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.muzzandroidexercise.data.MuzzMessage

@Database(entities = [MuzzMessage::class], version = 1)
abstract class MuzzDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}