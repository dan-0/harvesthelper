package com.idleoffice.harvesthelper.model.plants

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAll(): List<Plant>
}