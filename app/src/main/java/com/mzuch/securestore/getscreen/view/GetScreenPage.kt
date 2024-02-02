package com.mzuch.securestore.getscreen.view

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mzuch.securestore.getscreen.viewmodel.GetScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GetScreenPage(viewModel: GetScreenViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GetScreenView(uiState = uiState.value, getData = viewModel::getData)
    }
}

@Composable
fun GetScreenView(
    uiState: String, getData: (String) -> Unit
) {
    val maxLength = 20
    var keyText by rememberSaveable {
        mutableStateOf("")
    }
    KeyTextField(text = keyText, onValueChanged = {
        if (it.length <= maxLength) {
            keyText = it
        }
    })
    Spacer(modifier = Modifier.size(8.dp))
    GetButton(onClick = { getData(keyText) })
    Spacer(modifier = Modifier.size(24.dp))
    Text(text = uiState)
}

@Composable
fun KeyTextField(text: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChanged,
        maxLines = 1,
        label = { Text("Your key") },
    )
}

@Composable
fun GetButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("Get")
    }
}