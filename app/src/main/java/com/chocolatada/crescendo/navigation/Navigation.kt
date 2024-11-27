package com.chocolatada.crescendo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chocolatada.crescendo.ui.launch.LaunchScreen
import com.chocolatada.crescendo.ui.main.MainScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Launch) {
        composable<Launch> {
            LaunchScreen { navController.navigate(Main) }
        }
        composable<Main> {
            MainScreen { navController.popBackStack() }
        }
    }
}