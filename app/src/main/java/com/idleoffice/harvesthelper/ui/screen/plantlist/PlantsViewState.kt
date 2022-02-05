package com.idleoffice.harvesthelper.ui.screen.plantlist

import com.idleoffice.harvesthelper.model.plants.Plant

sealed class PlantsViewState {

    object Loading : PlantsViewState()

    object Error : PlantsViewState()

    data class Content(val plants: List<Plant>) : PlantsViewState()

}