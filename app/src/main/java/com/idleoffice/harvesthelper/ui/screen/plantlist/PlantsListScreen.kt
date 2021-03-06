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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idleoffice.harvesthelper.ui.component.search.SearchView
import com.idleoffice.harvesthelper.ui.screen.error.ErrorScreen
import com.idleoffice.harvesthelper.ui.screen.image.PlantImage
import com.idleoffice.harvesthelper.ui.screen.loading.LoadingScreen
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantListData
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantsViewState

private typealias NavigateToPlant = (PlantsListScreenIntent) -> Unit

@Composable
fun PlantsListScreen(
    vm: PlantsViewModel = hiltViewModel(),
    state: State<PlantsViewState> = vm.state.collectAsState(initial = PlantsViewState.Loading),
    navigateToPlantId: (PlantsListScreenIntent) -> Unit
) {

    when (val value = state.value) {
        is PlantsViewState.Content -> PlantsListContentView(value, navigateToPlantId)
        PlantsViewState.Error -> ErrorScreen()
        PlantsViewState.Loading -> LoadingScreen()
    }

}

@Composable
private fun PlantsListContentView(
    content: PlantsViewState.Content,
    navigateToPlantId: NavigateToPlant
) {

    val textFieldValue = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    val plants = filterPlants(textFieldValue.value.text, content)

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            SearchView(state = textFieldValue)
        }
        items(plants) {
            PlantListItem(it, navigateToPlantId)
        }
    }
}

private fun filterPlants(
    rawFilterString: String,
    content: PlantsViewState.Content
): List<PlantListData> {
    val searchText = rawFilterString.lowercase()

    val plants = if (searchText.isEmpty()) {
        content.plants
    } else {
        content.plants.filter {
            it.name.lowercase().contains(searchText)
        }
    }
    return plants
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
            .clickable {
                navigateToPlantId(PlantsListScreenIntent.OpenPlantDescription(it.id))
            },
        elevation = 2.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (it.image != null) {
                PlantImage(it.image, it.description)
            }
            Text(text = it.name)
        }
    }
}

sealed class PlantsListScreenIntent {

    data class OpenPlantDescription(
        val id: Int
    ) : PlantsListScreenIntent()

}