package com.idleoffice.harvesthelper.ui.screen.plantlist

import com.idleoffice.harvesthelper.model.plants.PlantDto

object PlantListScreenTestData {

    val TEST_PLANT_1 = PlantDto(
        id = 0,
        name = "Test plant 1",
        description = "Test plant 1 description",
        optimalSun = "Test plant 1 optimalSun",
        optimalSoil = "Test plant 1 optimalSoil",
        whenToPlant = "Test plant 1 whenToPlant",
        growingFromSeed = "Test plant 1 growingFromSeed",
        transplanting = "Test plant 1 transplanting",
        spacing = "Test plant 1 spacing",
        harvesting = "Test plant 1 harvesting",
        plantingConsiderations = "Test plant 1 plantingConsiderations",
        watering = "Test plant 1 watering",
        feeding = "Test plant 1 feeding",
        otherCare = "Test plant 1 otherCare",
        diseases = "Test plant 1 diseases",
        pests = null,
        storageUse = "Test plant 1 storageUse",
        image = null
    )

    val TEST_PLANT_2 = PlantDto(
        id = 0,
        name = "Test plant 2",
        description = "Test plant 2 description",
        optimalSun = "Test plant 2 optimalSun",
        optimalSoil = "Test plant 2 optimalSoil",
        whenToPlant = "Test plant 2 whenToPlant",
        growingFromSeed = "Test plant 2 growingFromSeed",
        transplanting = "Test plant 2 transplanting",
        spacing = "Test plant 2 spacing",
        harvesting = "Test plant 2 harvesting",
        plantingConsiderations = "Test plant 2 plantingConsiderations",
        watering = "Test plant 2 watering",
        feeding = "Test plant 2 feeding",
        otherCare = "Test plant 2 otherCare",
        diseases = "Test plant 2 diseases",
        pests = null,
        storageUse = "Test plant 2 storageUse",
        image = "i_01_tomato.webp"
    )

    val TEST_PLANTS = listOf(
        TEST_PLANT_1,
        TEST_PLANT_2
    )
}