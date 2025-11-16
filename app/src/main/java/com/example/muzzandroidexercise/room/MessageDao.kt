package com.example.muzzandroidexercise.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.muzzandroidexercise.data.MuzzMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    //get all messages from the database as a flow for real-time updates
    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<MuzzMessage>>

    @Insert
    suspend fun insertMessage(message: MuzzMessage)

}