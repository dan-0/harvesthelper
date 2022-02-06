package com.idleoffice.harvesthelper.ui.screen.plantdetails

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idleoffice.harvesthelper.R
import com.idleoffice.harvesthelper.ui.screen.error.ErrorView
import com.idleoffice.harvesthelper.ui.screen.image.PlantImage
import com.idleoffice.harvesthelper.ui.screen.loading.LoadingView
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsData
import com.idleoffice.harvesthelper.ui.screen.plantdetails.data.PlantDetailsViewState

@Composable
fun PlantDetailsScreen(
    plantId: Int,
    vm: PlantDetailViewModel = hiltViewModel()
) {

    when (val state = vm.state.collectAsState(initial = PlantDetailsViewState.Init).value) {
        is PlantDetailsViewState.Content -> PlantDetailsView(state.plant)
        PlantDetailsViewState.Error -> ErrorView()
        PlantDetailsViewState.Loading -> LoadingView()
        PlantDetailsViewState.Init -> {
            vm.loadPlantData(plantId)
            LoadingView()
        }
    }
}

@Composable
private fun PlantDetailsView(plant: PlantDetailsData) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        plant.image?.also {
            item {
                PlantImage(image = it, description = plant.description)
            }
        }

        addTitleAndDataItem(R.string.name, plant.name)

        addTitleAndDataItem(R.string.description, plant.description)

        addTitleAndDataItem(R.string.optimal_sun, plant.optimalSun)

        addTitleAndDataItem(R.string.optimal_soil, plant.optimalSoil)

        addTitleAndDataItem(R.string.when_to_plant, plant.whenToPlant)

        addTitleAndDataItem(R.string.growing_from_seed, plant.growingFromSeed)

        addTitleAndDataItem(R.string.transplanting, plant.transplanting)

        addTitleAndDataItem(R.string.spacing, plant.spacing)

        addTitleAndDataItem(R.string.planting_considerations, plant.plantingConsiderations)

        addTitleAndDataItem(R.string.watering, plant.watering)

        addTitleAndDataItem(R.string.feeding, plant.feeding)

        addTitleAndDataItem(R.string.other_care, plant.otherCare)

        addTitleAndDataItem(R.string.diseases, plant.diseases)

        addTitleAndDataItem(R.string.pests, plant.pests)

        addTitleAndDataItem(R.string.harvesting, plant.harvesting)

        addTitleAndDataItem(R.string.storage_use, plant.storageUse)
    }
}

@Composable
private fun TitleAndData(@StringRes title: Int, name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(title),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(name)
    }
}

private fun LazyListScope.addTitleAndDataItem(@StringRes title: Int, name: String?) {
    if (name.isNullOrEmpty()) return

    item {
        TitleAndData(title = title, name = name)
    }
}
