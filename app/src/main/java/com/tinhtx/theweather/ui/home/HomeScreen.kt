package com.tinhtx.theweather.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tinhtx.theweather.ui.home.tabs.hometab.HomeTabScreen
import com.tinhtx.theweather.ui.home.tabs.location.LocationScreen
import com.tinhtx.theweather.ui.home.tabs.settings.SettingsScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                getBottomNavItems().forEach { item ->
                    NavigationBarItem(
                        selected = uiState.selectedTab == item.route,
                        onClick = {
                            viewModel.onTabSelected(item.route)
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeTabScreen()
            }
            composable(BottomNavItem.Location.route) {
                LocationScreen()
            }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
