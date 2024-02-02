package com.mzuch.securestore.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(val route: String, val iconVector: ImageVector, val label: String) {
    data object GetScreen : NavScreen("getPage", Icons.Default.Search, "Get")
    data object SaveScreen : NavScreen("savePage", Icons.Default.Add, "Save")
}