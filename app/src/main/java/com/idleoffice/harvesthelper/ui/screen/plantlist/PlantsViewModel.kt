package com.idleoffice.harvesthelper.ui.screen.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idleoffice.harvesthelper.model.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {

    private val _state = MutableStateFlow<PlantsViewState>(PlantsViewState.Loading)
    val state: Flow<PlantsViewState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = PlantsViewState.Content(db.plantDao().getAll())
        }
    }

}