package com.idleoffice.harvesthelper.model.plants

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class PlantDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "optimal_sun") val optimalSun: String,
    @ColumnInfo(name = "optimal_soil") val optimalSoil: String,
    @ColumnInfo(name = "when_to_plant") val whenToPlant: String,
    @ColumnInfo(name = "growing_from_seed") val growingFromSeed: String,
    @ColumnInfo(name = "transplanting") val transplanting: String,
    @ColumnInfo(name = "spacing") val spacing: String,
    @ColumnInfo(name = "harvesting") val harvesting: String,
    @ColumnInfo(name = "planting_considerations") val plantingConsiderations: String?,
    @ColumnInfo(name = "watering") val watering: String?,
    @ColumnInfo(name = "feeding") val feeding: String?,
    @ColumnInfo(name = "other_care") val otherCare: String?,
    @ColumnInfo(name = "diseases") val diseases: String?,
    @ColumnInfo(name = "pests") val pests: String?,
    @ColumnInfo(name = "storage_use") val storageUse: String?,
    @ColumnInfo(name = "image") val image: String?,
)