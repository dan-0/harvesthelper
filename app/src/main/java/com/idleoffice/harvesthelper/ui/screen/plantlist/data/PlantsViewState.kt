package com.idleoffice.harvesthelper.ui.screen.plantlist.data

sealed class PlantsViewState {

    object Loading : PlantsViewState()

    object Error : PlantsViewState()

    data class Content(
        val plants: List<PlantListData>
    ) : PlantsViewState()

}