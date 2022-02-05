package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idleoffice.harvesthelper.ui.screen.error.ErrorView
import com.idleoffice.harvesthelper.ui.screen.image.PlantImage
import com.idleoffice.harvesthelper.ui.screen.loading.LoadingView
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantListData
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantsViewState
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme

private typealias NavigateToPlant = (Int) -> Unit

@Composable
fun PlantsListScreen(
    viewModel: PlantsViewModel = hiltViewModel(),
    navigateToPlantId: (Int) -> Unit
) {

    val state = viewModel.state.collectAsState(initial = PlantsViewState.Loading)

    when (val value = state.value) {
        is PlantsViewState.Content -> PlantsListContentView(value, navigateToPlantId)
        PlantsViewState.Error -> ErrorView()
        PlantsViewState.Loading -> LoadingView()
    }
}

@Composable
private fun PlantsListContentView(
    content: PlantsViewState.Content,
    navigateToPlantId: NavigateToPlant
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(content.plants) {
            PlantListItem(it, navigateToPlantId)
        }
    }
}

@Composable
private fun PlantListItem(
    it: PlantListData,
    navigateToPlantId: NavigateToPlant
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { navigateToPlantId(it.id) },
        elevation = 2.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (it.image != null) {
                PlantImage(it.image, it.image)
            }
            Text(text = it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val previewPlant = PlantListData(
        id = 0,
        name = "Test plant",
        description = "Don't eat me please",
        image = null
    )
    HarvestHelperTheme {
        PlantsListContentView(PlantsViewState.Content(listOf(previewPlant))) {}
    }
}