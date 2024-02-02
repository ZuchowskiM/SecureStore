package com.mzuch.securestore.savescreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveScreenPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SaveKeyTextField()
        Spacer(modifier = Modifier.size(8.dp))
        ValueTextField()
        Spacer(modifier = Modifier.size(8.dp))
        SaveButton()
    }
}

@Composable
fun SaveKeyTextField() {
    var text by remember {
        mutableStateOf("")
    }
    val maxLength = 20;
    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= maxLength) {
                text = it
            }
        },
        maxLines = 1,
        label = { Text("Your text") },
    )
}

@Composable
fun ValueTextField() {
    var text by remember {
        mutableStateOf("")
    }
    val maxLength = 20;
    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= maxLength) {
                text = it
            }
        },
        maxLines = 1,
        label = { Text("Your key") },
    )
}

@Composable
fun SaveButton() {
    OutlinedButton(onClick = { }) {
        Text("Save")
    }
}