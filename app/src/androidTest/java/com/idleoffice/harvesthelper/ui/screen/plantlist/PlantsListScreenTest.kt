package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.idleoffice.harvesthelper.MainActivity
import com.idleoffice.harvesthelper.di.modules.database.PlantDaoModule
import com.idleoffice.harvesthelper.model.plants.PlantDao
import com.idleoffice.harvesthelper.model.plants.PlantDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@UninstallModules(PlantDaoModule::class)
@HiltAndroidTest
class PlantsListScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun happyPath() {
        // Start the app
        composeTestRule.setContent {
            PlantsListScreen(navigateToPlantId = {})
        }

        composeTestRule.onNodeWithText("Test plant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test plant 2").assertIsDisplayed()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    class TestPlantDaoModule {

        @Singleton
        @Provides
        fun providePlantDao(): PlantDao {

            return object : PlantDao {
                override fun getAll(): Flow<List<PlantDto>> {
                    return flow {
                        emit(PlantListScreenTestData.TEST_PLANTS)
                    }
                }

                override suspend fun getPlant(id: Int): PlantDto? {
                    return PlantListScreenTestData.TEST_PLANTS.firstOrNull {
                        it.id == id
                    }
                }

            }
        }
    }

}