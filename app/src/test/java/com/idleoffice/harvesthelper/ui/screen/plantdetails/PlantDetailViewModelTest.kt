package com.idleoffice.harvesthelper.ui.screen.plantdetails

import com.idleoffice.harvesthelper.model.plants.PlantDao
import com.idleoffice.harvesthelper.model.plants.PlantDto
import com.idleoffice.harvesthelper.test.util.BaseUnitTest
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsData
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsViewState
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlantDetailViewModelTest : BaseUnitTest() {

    @RelaxedMockK
    lateinit var plantDao: PlantDao

    private lateinit var ut: PlantDetailViewModel

    private lateinit var states: List<PlantDetailsViewState>

    override fun setup() {
        super.setup()

        ut = PlantDetailViewModel(
            plantDao,
            testDispatcher
        )

        states = ut.state.toLiveList()
    }

    @Test
    fun init() = coRunTest {
        val expected = PlantDetailsViewState.Init

        // then
        assertEquals(expected, states[0])
        assertEquals(1, states.size)
    }

    @Test
    fun `load plant data happy path`() = coRunTest {
        val expectedStates = listOf(
            PlantDetailsViewState.Init,
            PlantDetailsViewState.Loading,
            PlantDetailsViewState.Content(
                PlantDetailsData(
                    name = FAKE_PLANT_DTO.name,
                    description = FAKE_PLANT_DTO.description,
                    optimalSun = FAKE_PLANT_DTO.optimalSun,
                    optimalSoil = FAKE_PLANT_DTO.optimalSoil,
                    whenToPlant = FAKE_PLANT_DTO.whenToPlant,
                    growingFromSeed = FAKE_PLANT_DTO.growingFromSeed,
                    transplanting = FAKE_PLANT_DTO.transplanting,
                    spacing = FAKE_PLANT_DTO.spacing,
                    plantingConsiderations = FAKE_PLANT_DTO.plantingConsiderations,
                    watering = FAKE_PLANT_DTO.watering,
                    feeding = FAKE_PLANT_DTO.feeding,
                    otherCare = FAKE_PLANT_DTO.otherCare,
                    diseases = FAKE_PLANT_DTO.diseases,
                    pests = FAKE_PLANT_DTO.pests,
                    harvesting = FAKE_PLANT_DTO.harvesting,
                    storageUse = FAKE_PLANT_DTO.storageUse,
                    image = FAKE_PLANT_DTO.image
                )
            )
        )

        // given
        coEvery {
            plantDao.getPlant(FAKE_PLANT_DTO.id)
        } returns FAKE_PLANT_DTO

        // when
        ut.loadPlantData(FAKE_PLANT_DTO.id)
        advanceUntilIdle()

        // then
        assertEquals(expectedStates, states)
    }

    @Test
    fun `loadPlantData bad id`() = coRunTest {
        val expectedStates = listOf(
            PlantDetailsViewState.Init,
            PlantDetailsViewState.Loading,
            PlantDetailsViewState.Error
        )

        // given
        coEvery {
            plantDao.getPlant(any())
        } returns null

        // when
        ut.loadPlantData(0)
        advanceUntilIdle()

        // then
        assertEquals(expectedStates, states)
    }

    companion object {

        private val FAKE_PLANT_DTO = PlantDto(
            id = 1,
            name = "name",
            description = "description",
            optimalSun = "optimalSun",
            optimalSoil = "optimalSoil",
            whenToPlant = "whenToPlant",
            growingFromSeed = "growingFromSeed",
            transplanting = "transplanting",
            spacing = "spacing",
            harvesting = "harvesting",
            plantingConsiderations = "plantingConsiderations",
            watering = "watering",
            feeding = "feeding",
            otherCare = "otherCare",
            diseases = "diseases",
            pests = null,
            storageUse = "storageUse",
            image = null
        )

    }
}