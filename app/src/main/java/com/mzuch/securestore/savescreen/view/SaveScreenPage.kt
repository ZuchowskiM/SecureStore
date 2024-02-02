package com.mzuch.securestore.savescreen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mzuch.securestore.savescreen.viewmodel.SaveScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SaveScreenPage(
    viewModel: SaveScreenViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SaveScreenView(
            uiState = uiState.value,
            saveData = viewModel::saveData
        )
    }
}

@Composable
fun SaveScreenView(
    uiState: String,
    saveData: (String, String) -> Unit
) {
    val maxLength = 20
    var keyText by rememberSaveable {
        mutableStateOf("")
    }
    var valueText by remember {
        mutableStateOf("")
    }
    SaveKeyTextField(
        keyText,
        onValueChange = {
            if (it.length <= maxLength) {
                keyText = it
            }
        },
    )
    Spacer(modifier = Modifier.size(8.dp))
    ValueTextField(
        valueText,
        onValueChange = {
            if (it.length <= maxLength) {
                valueText = it
            }
        },
    )
    Spacer(modifier = Modifier.size(8.dp))
    SaveButton(onClick = { saveData(keyText, valueText) })
    Spacer(modifier = Modifier.size(24.dp))
    Text(text = uiState)
}

@Composable
fun SaveKeyTextField(keyText: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = keyText,
        onValueChange = onValueChange,
        maxLines = 1,
        label = { Text("Your Key") },
    )
}

@Composable
fun ValueTextField(valueText: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = valueText,
        onValueChange = onValueChange,
        maxLines = 1,
        label = { Text("Your Text") },
    )
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("Save")
    }
}