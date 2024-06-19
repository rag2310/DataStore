package com.rago.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.datastore.state.MenuUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<MenuUIState> = MutableStateFlow(
        MenuUIState(
            setOnNavDataStoreScreen = ::setOnNavDataStoreScreen
        )
    )
    val uiState: StateFlow<MenuUIState> = _uiState.asStateFlow()

    private fun setOnNavDataStoreScreen(onNavDataStoreScreen: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(onNavDataStoreScreen = onNavDataStoreScreen)
            }
        }
    }
}