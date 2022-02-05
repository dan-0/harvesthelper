package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idleoffice.harvesthelper.di.modules.dispatchers.DispatcherProvider
import com.idleoffice.harvesthelper.model.AppDatabase
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantListData
import com.idleoffice.harvesthelper.ui.screen.plantlist.data.PlantsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val db: AppDatabase,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _state = MutableStateFlow<PlantsViewState>(PlantsViewState.Loading)
    val state: Flow<PlantsViewState> = _state

    init {
        viewModelScope.launch {
            db.plantDao().getAll().collect { plants ->
                val data = plants.map {
                    PlantListData(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        image = it.image
                    )
                }

                _state.value = PlantsViewState.Content(data)
            }
        }
    }

}