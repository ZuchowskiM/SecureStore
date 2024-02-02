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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GetScreenPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        KeyTextField()
        Spacer(modifier = Modifier.size(8.dp))
        GetButton()
    }
}

@Composable
fun KeyTextField() {
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
fun GetButton() {
    OutlinedButton(onClick = { }) {
        Text("Get")
    }
}