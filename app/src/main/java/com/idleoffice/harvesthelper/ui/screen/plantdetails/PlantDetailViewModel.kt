package com.idleoffice.harvesthelper.ui.screen.plantdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idleoffice.harvesthelper.di.modules.dispatchers.DispatcherProvider
import com.idleoffice.harvesthelper.di.modules.dispatchers.buildHandledIoContext
import com.idleoffice.harvesthelper.model.plants.PlantDao
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsData
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val plantDao: PlantDao,
    dispatchers: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow<PlantDetailsViewState>(PlantDetailsViewState.Init)
    val state: Flow<PlantDetailsViewState> = _state

    private val ioContext = dispatchers.buildHandledIoContext {
        _state.value = PlantDetailsViewState.Error
    }

    fun loadPlantData(plantId: Int) {
        _state.value = PlantDetailsViewState.Loading
        viewModelScope.launch(ioContext) {
            val plantDto = plantDao.getPlant(plantId)

            val plantDetails = plantDto?.let {
                PlantDetailsData(
                    name = it.name,
                    description = it.description,
                    optimalSun = it.optimalSun,
                    optimalSoil = it.optimalSoil,
                    plantingConsiderations = it.plantingConsiderations,
                    whenToPlant = it.whenToPlant,
                    growingFromSeed = it.growingFromSeed,
                    transplanting = it.transplanting,
                    spacing = it.spacing,
                    watering = it.watering,
                    feeding = it.feeding,
                    otherCare = it.otherCare,
                    diseases = it.diseases,
                    pests = it.pests,
                    harvesting = it.harvesting,
                    storageUse = it.storageUse,
                    image = it.image
                )
            }

            _state.value = if (plantDetails != null) {
                PlantDetailsViewState.Content(plantDetails)
            } else {
                PlantDetailsViewState.Error
            }
        }
    }

}