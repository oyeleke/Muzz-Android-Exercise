package com.example.muzzandroidexercise.repositories

import com.example.muzzandroidexercise.data.MuzzMessage
import com.example.muzzandroidexercise.room.MessageDao
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    val allItems : Flow<List<MuzzMessage>>

    suspend fun insertMessage(message: MuzzMessage)
}

class MessageRepositoryImpl(
    private val messageDao: MessageDao
) : MessageRepository {

    override val allItems: Flow<List<MuzzMessage>> = messageDao.getAllMessages()

    override suspend fun insertMessage(message: MuzzMessage) {
        messageDao.insertMessage(message)
    }
}