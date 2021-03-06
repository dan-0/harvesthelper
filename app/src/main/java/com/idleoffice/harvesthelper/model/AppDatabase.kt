package com.idleoffice.harvesthelper.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idleoffice.harvesthelper.model.plants.PlantDto
import com.idleoffice.harvesthelper.model.plants.PlantDao

@Database(entities = [PlantDto::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}