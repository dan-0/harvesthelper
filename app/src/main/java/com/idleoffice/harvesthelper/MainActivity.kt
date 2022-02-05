package com.idleoffice.harvesthelper

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idleoffice.harvesthelper.model.plants.Plant
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HarvestHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlantsListView()
                }
            }
        }
    }
}

@Composable
fun PlantsListView(viewModel: PlantsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState(initial = PlantsViewState.Loading)

   when (val value = state.value) {
       is PlantsViewState.Content -> PlantsListItemView(value)
       PlantsViewState.Error -> ErrorView()
       PlantsViewState.Loading -> LoadingView()
   }
}

@Composable
fun PlantsListItemView(content: PlantsViewState.Content) {
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

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error",
            textAlign = TextAlign.Center
        )
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
        PlantsListItemView(PlantsViewState.Content(listOf(previewPlant)))
    }
}
