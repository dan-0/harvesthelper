package com.idleoffice.harvesthelper.nav

import androidx.annotation.StringRes
import androidx.navigation.NavBackStackEntry
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

    object PlantDetails : AppDestinations(
        "PlantDetails/{plantId}",
        R.string.plant_details
    ) {
        private const val paramItemId = "plantId"

        override val routeTemplate = "$baseRoute/{$paramItemId}"

        fun buildRoute(plantId: Int): String = "$baseRoute/$plantId"

        fun itemIdFromNav(backStackEntry: NavBackStackEntry): Int {
            return backStackEntry.arguments!!.getString(paramItemId)?.toInt() ?: -1
        }
    }
}