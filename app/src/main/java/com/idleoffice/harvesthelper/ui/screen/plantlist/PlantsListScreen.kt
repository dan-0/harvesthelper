package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.idleoffice.harvesthelper.model.plants.Plant
import com.idleoffice.harvesthelper.ui.screen.error.ErrorView
import com.idleoffice.harvesthelper.ui.screen.loading.LoadingView
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme

@Composable
fun PlantsListScreen(viewModel: PlantsViewModel = hiltViewModel()) {

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
            if (it.image != null) {
                val uri = "android.resource://com.idleoffice.harvesthelper/raw/${it.image.substringBeforeLast(".")}"
                Box(
                    modifier = Modifier.background(Color.Gray).height(200.dp).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = rememberImagePainter(uri),
                        contentDescription = it.description,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
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