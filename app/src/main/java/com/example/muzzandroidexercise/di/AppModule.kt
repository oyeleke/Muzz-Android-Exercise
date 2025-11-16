package com.example.muzzandroidexercise.di

import android.content.Context
import androidx.room.Room
import com.example.muzzandroidexercise.repositories.MessageRepository
import com.example.muzzandroidexercise.repositories.MessageRepositoryImpl
import com.example.muzzandroidexercise.room.MessageDao
import com.example.muzzandroidexercise.room.MuzzDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMuzzDatabase(@ApplicationContext context: Context): MuzzDatabase =
        Room.databaseBuilder(
            context,
            MuzzDatabase::class.java,
            "muzz_database"
        ).fallbackToDestructiveMigration(dropAllTables = true)
            .build(
            )

    @Provides
    fun provideMessageDao(muzzDatabase: MuzzDatabase) : MessageDao = muzzDatabase.messageDao()

    @Provides
    fun provideMuzzRepository(messageDao: MessageDao) : MessageRepository = MessageRepositoryImpl(messageDao)

}