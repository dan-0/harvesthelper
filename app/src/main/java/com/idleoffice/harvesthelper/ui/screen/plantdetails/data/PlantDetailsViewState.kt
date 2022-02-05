package com.idleoffice.harvesthelper.ui.screen.plantdetails.data

sealed class PlantDetailsViewState {

    object Init : PlantDetailsViewState()

    object Loading : PlantDetailsViewState()

    object Error : PlantDetailsViewState()

    data class Content(val plant: PlantDetailsData) : PlantDetailsViewState()

}