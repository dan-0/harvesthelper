package com.idleoffice.harvesthelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.idleoffice.harvesthelper.nav.AppDestinations
import com.idleoffice.harvesthelper.ui.screen.plantdetails.PlantDetailsScreen
import com.idleoffice.harvesthelper.ui.screen.plantlist.PlantsListScreen
import com.idleoffice.harvesthelper.ui.screen.plantlist.PlantsListScreenIntent
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    @Composable
    private fun App() {
        val navController = rememberNavController()

        val title = remember { mutableStateOf("") }

        val updateTitle = { newTitle: String ->
            title.value = newTitle
        }

        HarvestHelperTheme {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(title.value) },
                        elevation = 20.dp
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = AppDestinations.PlantsList.routeTemplate,
                    modifier = Modifier.padding(innerPadding),
                ) {

                    composableDestination(AppDestinations.PlantsList) { _, destination ->
                        updateTitle(stringResource(destination.title))
                        PlantsListScreen {
                            plantListScreenNavigator(it, navController)
                        }
                    }
                    
                    composableDestination(AppDestinations.PlantDetails) { backStackEntry, destination ->
                        updateTitle(stringResource(destination.title))
                        PlantDetailsScreen(plantId = destination.itemIdFromNav(backStackEntry))
                    }
                }

            }
        }

    }

    private fun <T : AppDestinations> NavGraphBuilder.composableDestination(
        destination: T,
        content: @Composable (backStackEntry: NavBackStackEntry, destination: T) -> Unit
    ) {
        composable(destination.routeTemplate) {
            content(it, destination)
        }
    }

    private fun plantListScreenNavigator(
        intent: PlantsListScreenIntent,
        navController: NavHostController
    ) {
        when (intent) {
            is PlantsListScreenIntent.OpenPlantDescription -> {
                navController.navigate(
                    AppDestinations.PlantDetails.buildRoute(intent.id)
                )
            }
        }
    }
}


