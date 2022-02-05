package com.idleoffice.harvesthelper.model.plants

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun getAll(): Flow<List<PlantDto>>

    @Query("SELECT * FROM plants WHERE id=:id")
    suspend fun getPlant(id: Int): PlantDto?
}