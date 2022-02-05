package com.idleoffice.harvesthelper.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idleoffice.harvesthelper.model.plants.Plant
import com.idleoffice.harvesthelper.model.plants.PlantDao

@Database(entities = [Plant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}