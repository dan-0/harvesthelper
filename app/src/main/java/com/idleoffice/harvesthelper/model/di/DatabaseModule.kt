package com.idleoffice.harvesthelper.model.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.idleoffice.harvesthelper.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Plants.db"
        )
            .createFromAsset("database/plants.db")
            .build()
    }

}