package com.idleoffice.harvesthelper.di.modules.database

import com.idleoffice.harvesthelper.model.AppDatabase
import com.idleoffice.harvesthelper.model.plants.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PlantDaoModule {

    @Provides
    fun providePlantDao(db: AppDatabase): PlantDao {
        return db.plantDao()
    }

}