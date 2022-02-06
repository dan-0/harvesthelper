package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import com.idleoffice.harvesthelper.di.modules.database.PlantDaoModule
import com.idleoffice.harvesthelper.model.plants.PlantDao
import com.idleoffice.harvesthelper.testutils.TestActivity
import com.idleoffice.harvesthelper.ui.screen.error.ErrorScreenTest
import com.idleoffice.harvesthelper.ui.screen.loading.LoadingScreenTest
import com.idleoffice.harvesthelper.ui.screen.plantlist.PlantListScreenTestData.TEST_PLANTS
import com.idleoffice.harvesthelper.ui.screen.plantlist.PlantListScreenTestData.TEST_PLANT_1
import com.idleoffice.harvesthelper.ui.screen.plantlist.PlantListScreenTestData.TEST_PLANT_2
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantListData
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantsViewState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(PlantDaoModule::class)
@HiltAndroidTest
class PlantsListScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(TestActivity::class.java)

    @Inject
    lateinit var plantDao: PlantDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Example mocking an async resource for testing VM/View initialization behavior.
     * This is useful in testing more complex behavior between the VM and View
     */
    @Test
    fun mockDaoForContent() {
        // given

        // because we're injecting the Dao, we can keep idling resources in the tests without
        // polluting production code
        val idlingResource = CountingIdlingResource("test")
        IdlingRegistry.getInstance().register(idlingResource)
        idlingResource.increment()
        
        every {
            plantDao.getAll()
        } returns flow {
            emit(TEST_PLANTS)
            idlingResource.decrement()
        }

        composeTestRule.setContent {
            PlantsListScreen(navigateToPlantId = {})
        }

        // then
        verifyContentState()
    }

    @Test
    fun happyPath() {
        // given
        composeTestRule.setContent {
            PlantsListScreen(
                state = mutableStateOf(TEST_CONTENT),
                navigateToPlantId = {}
            )
        }

        // then
        verifyContentState()
    }

    private fun verifyContentState() {
        composeTestRule.onNodeWithText(
            TEST_PLANT_1.name
        ).assertIsDisplayed()
        // image is null on plant 1, so not displayed
        composeTestRule.onNodeWithContentDescription(
            TEST_PLANT_1.description
        ).assertDoesNotExist()

        composeTestRule.onNodeWithText(
            TEST_PLANT_2.name
        ).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(
            TEST_PLANT_2.description
        ).assertIsDisplayed()
    }

    @Test
    fun verifyPlantDetailsIntent() {
        // given
        val expected = PlantsListScreenIntent.OpenPlantDescription(TEST_PLANT_1.id)
        var lastIntent: PlantsListScreenIntent? = null
        composeTestRule.setContent {
            PlantsListScreen(
                state = mutableStateOf(TEST_CONTENT),
                navigateToPlantId = {
                    lastIntent = it
                }
            )
        }

        // when
        composeTestRule.onNodeWithText(
            TEST_PLANT_1.name
        ).performClick()

        // then
        assertEquals(expected, lastIntent)
    }

    @Test
    fun loadingState() {
        // given
        composeTestRule.setContent {
            PlantsListScreen(
                state = mutableStateOf(PlantsViewState.Loading),
                navigateToPlantId = {}
            )
        }

        // then
        LoadingScreenTest.verifyLoadingScreenVisible(composeTestRule)
    }

    @Test
    fun errorState() {
        // given
        composeTestRule.setContent {
            PlantsListScreen(
                state = mutableStateOf(PlantsViewState.Error),
                navigateToPlantId = {}
            )
        }

        // then
        ErrorScreenTest.verifyErrorScreenVisible(composeTestRule)
    }

    companion object {

        private val TEST_CONTENT = PlantsViewState.Content(
            TEST_PLANTS.map {
                PlantListData(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.image
                )
            }
        )

    }

}

