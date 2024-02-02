package com.mzuch.securestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mzuch.securestore.getscreen.view.GetScreenPage
import com.mzuch.securestore.navigation.NavScreen
import com.mzuch.securestore.savescreen.view.SaveScreenPage
import com.mzuch.securestore.ui.theme.SecureStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureStoreTheme(
                darkTheme = false,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SecureStoreApp()
                }
            }
        }
    }
}

@Composable
fun SecureStoreApp() {
    val navItems = listOf(NavScreen.GetScreen, NavScreen.SaveScreen)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavBar(navItems, navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavScreen.GetScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavScreen.GetScreen.route) {
                GetScreenPage()
            }
            composable(NavScreen.SaveScreen.route) {
                SaveScreenPage()
            }
        }
    }
}

@Composable
fun AppBottomNavBar(navItems: List<NavScreen>, navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.iconVector,
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.label) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
