package com.idleoffice.harvesthelper.ui.screen.plantlist

import com.idleoffice.harvesthelper.model.plants.PlantDao
import com.idleoffice.harvesthelper.model.plants.PlantDto
import com.idleoffice.harvesthelper.test.util.BaseUnitTest
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantListData
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantsViewState
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlantsViewModelTest : BaseUnitTest(){

    @RelaxedMockK
    private lateinit var plantsDao: PlantDao

    private lateinit var ut: PlantsViewModel

    private lateinit var states: List<PlantsViewState>

    override fun setup() {
        super.setup()

        ut = PlantsViewModel(
            plantsDao,
            testDispatcher
        )

        states = ut.state.toLiveList()
    }

    @Test
    fun init() = coRunTest {
        val expectedFirst = PlantsViewState.Loading
        val expectedLast = PlantsViewState.Content(CONVERTED_PLANT_LIST_DATA)

        // given
        every {
            plantsDao.getAll()
        } returns flowOf(FAKE_PLANTS_DTO_LIST)

        // when
        advanceUntilIdle()

        // then
        assertEquals(expectedFirst, states.first())
        assertEquals(expectedLast, states.last())
    }

    companion object {

        private val FAKE_PLANTS_DTO_LIST = listOf(
            PlantDto(
                id = 1,
                name = "test1",
                description = "test1",
                optimalSun = "",
                optimalSoil = "",
                whenToPlant = "",
                growingFromSeed = "",
                transplanting = "",
                spacing = "",
                harvesting = "",
                plantingConsiderations = null,
                watering = null,
                feeding = null,
                otherCare = null,
                diseases = null,
                pests = null,
                storageUse = null,
                image = null
            ),
            PlantDto(
                id = 2,
                name = "test2",
                description = "test2",
                optimalSun = "",
                optimalSoil = "",
                whenToPlant = "",
                growingFromSeed = "",
                transplanting = "",
                spacing = "",
                harvesting = "",
                plantingConsiderations = null,
                watering = null,
                feeding = null,
                otherCare = null,
                diseases = null,
                pests = null,
                storageUse = null,
                image = null
            ),
        )

        private val CONVERTED_PLANT_LIST_DATA = listOf(
            PlantListData(
                1,
                "test1",
                "test1",
                null
            ),
            PlantListData(
                2,
                "test2",
                "test2",
                null
            )
        )

    }
}