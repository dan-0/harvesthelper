package com.idleoffice.harvesthelper.di.modules.database

import android.app.Application
import androidx.room.Room
import com.idleoffice.harvesthelper.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Plants.db",
        )
            .createFromAsset("database/plants.db")
            .build()
    }

}