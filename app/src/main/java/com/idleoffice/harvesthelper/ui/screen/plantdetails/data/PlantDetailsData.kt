package com.idleoffice.harvesthelper.ui.screen.plantdetails.data

data class PlantDetailsData(
    val name: String,
    val description: String,
    val optimalSun: String,
    val optimalSoil: String,
    val whenToPlant: String,
    val growingFromSeed: String,
    val transplanting: String,
    val spacing: String,
    val plantingConsiderations: String?,
    val watering: String?,
    val feeding: String?,
    val otherCare: String?,
    val diseases: String?,
    val pests: String?,
    val harvesting: String,
    val storageUse: String?,
    val image: String?,
)