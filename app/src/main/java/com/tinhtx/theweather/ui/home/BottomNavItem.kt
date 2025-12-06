package com.tinhtx.theweather.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home_tab", "Home", Icons.Default.Home)
    object Location : BottomNavItem("location_tab", "Location", Icons.Default.LocationOn)
    object Settings : BottomNavItem("settings_tab", "Settings", Icons.Default.Settings)
}

fun getBottomNavItems() = listOf(
    BottomNavItem.Home,
    BottomNavItem.Location,
    BottomNavItem.Settings
)
