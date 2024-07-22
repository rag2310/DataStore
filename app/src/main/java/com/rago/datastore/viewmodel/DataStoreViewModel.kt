package com.rago.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.datastore.data.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<DataStoreUIState> = MutableStateFlow(
        DataStoreUIState(
            setName = ::setName,
            onChangeName = ::onChangeName
        )
    )
    val uiState: StateFlow<DataStoreUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.getName().collect { name ->
                _uiState.update { dataStoreUIState ->
                    dataStoreUIState.copy(name = name)
                }
            }
        }
    }

    private fun setName() {
        viewModelScope.launch {
            dataStoreRepository.setName(_uiState.value.inputName)
        }
    }

    private fun onChangeName(value: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(inputName = value)
            }
        }
    }
}