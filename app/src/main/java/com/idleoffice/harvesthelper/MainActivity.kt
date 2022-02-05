package com.idleoffice.harvesthelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.idleoffice.harvesthelper.model.AppDatabase
import com.idleoffice.harvesthelper.ui.theme.HarvestHelperTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HarvestHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Hello", db)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, db: AppDatabase) {

    val newName = remember {
        mutableStateOf(name)
    }
    val scope = rememberCoroutineScope()

    Text(text = "Hello ${newName.value}!", Modifier.clickable {
        scope.launch(Dispatchers.IO) {
            val name = db.plantDao().getAll().firstOrNull().toString()
            withContext(Dispatchers.Main) {
                newName.value = name
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HarvestHelperTheme {
//        Greeting("Android")
    }
}