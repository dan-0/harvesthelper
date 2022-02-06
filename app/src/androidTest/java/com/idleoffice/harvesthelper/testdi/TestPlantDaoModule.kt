package com.idleoffice.harvesthelper.testdi

import com.idleoffice.harvesthelper.di.modules.database.PlantDaoModule
import com.idleoffice.harvesthelper.model.plants.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

/**
 * Replaces [PlantDaoModule] for UI tests
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PlantDaoModule::class]
)
class TestPlantDaoModule {

    @Singleton
    @Provides
    fun providePlantDao(): PlantDao {
        return mockk()
    }

}