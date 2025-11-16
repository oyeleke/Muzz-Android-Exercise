package com.example.muzzandroidexercise.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.muzzandroidexercise.data.MuzzMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<MuzzMessage>>

    @Insert
    suspend fun insertMessage(message: MuzzMessage)

}