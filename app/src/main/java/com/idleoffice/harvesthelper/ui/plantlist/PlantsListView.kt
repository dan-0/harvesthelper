package com.idleoffice.harvesthelper.ui.plantlist

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idleoffice.harvesthelper.model.plants.Plant
import com.idleoffice.harvesthelper.ui.error.ErrorView
import com.idleoffice.harvesthelper.ui.loading.LoadingView
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme

@Composable
fun PlantsListView(viewModel: PlantsViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState(initial = PlantsViewState.Loading)

    when (val value = state.value) {
        is PlantsViewState.Content -> PlantsListContentView(value)
        PlantsViewState.Error -> ErrorView()
        PlantsViewState.Loading -> LoadingView()
    }
}

@Composable
private fun PlantsListContentView(content: PlantsViewState.Content) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(content.plants) {
            PlantListItem(it)
        }
    }
}

@Composable
private fun PlantListItem(it: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val file = LocalContext.current.assets.open("raw/${it.image}")

            val bitmap = BitmapFactory.decodeStream(file)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = it.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
            )
            Text(text = it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val previewPlant = Plant(
        id = 0,
        name = "Test plant",
        description = "Don't eat me please",
        optimalSun = "Sounds shady",
        optimalSoil = "Not so basic",
        plantingConsiderations = null,
        whenToPlant = "When in doubt",
        growingFromSeed = "iterate iterate iterate",
        transplanting = "very possible",
        spacing = "take as needed",
        watering = null,
        feeding = null,
        otherCare = null,
        diseases = null,
        pests = null,
        harvesting = "",
        storageUse = null,
        image = null
    )
    HarvestHelperTheme {
        PlantsListContentView(PlantsViewState.Content(listOf(previewPlant)))
    }
}