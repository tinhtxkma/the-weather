package com.tinhtx.theweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tinhtx.theweather.ui.home.HomeScreen
import com.tinhtx.theweather.ui.splash.SplashScreen
import com.tinhtx.theweather.ui.welcome.WelcomeScreen

@Composable
fun NavGraph(
    startDestination: String = Screen.Splash.route
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToWelcome = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
