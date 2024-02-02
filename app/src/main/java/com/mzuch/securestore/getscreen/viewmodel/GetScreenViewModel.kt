package com.mzuch.securestore.getscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzuch.securestore.repository.ValueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GetScreenViewModel(private val valueRepository: ValueRepository) : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    fun getData(key: String) {
        viewModelScope.launch {
            val value = valueRepository.getValue(key)
            if (value != null) {
                _uiState.value = value
            } else {
                _uiState.value = "No data found"
            }
        }
    }
}