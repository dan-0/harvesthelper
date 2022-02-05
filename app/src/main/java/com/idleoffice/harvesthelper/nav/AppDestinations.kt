package com.idleoffice.harvesthelper.nav

import androidx.annotation.StringRes
import com.idleoffice.harvesthelper.R

sealed class AppDestinations(
    val baseRoute: String,
    @StringRes val title: Int
) {

    open val routeTemplate: String = baseRoute

    object PlantsList : AppDestinations(
        "PlantsList",
        R.string.plants_list,
    )
}