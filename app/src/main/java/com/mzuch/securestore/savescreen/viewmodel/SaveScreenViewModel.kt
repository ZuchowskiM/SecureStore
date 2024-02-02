package com.mzuch.securestore.savescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzuch.securestore.repository.ValueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaveScreenViewModel(private val valueRepository: ValueRepository) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    fun saveData(key: String, value: String) {
        viewModelScope.launch {
            if (key.isBlank() || value.isBlank()) {
                _uiState.update { "Key and value cannot be empty" }
                return@launch
            }
            val isSuccess = valueRepository.saveValue(key, value)
            if (isSuccess) {
                _uiState.update { "Data saved successfully" }
            } else {
                _uiState.update { "Error while saving data" }
            }
        }
    }
}