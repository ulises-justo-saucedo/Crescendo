package com.chocolatada.crescendo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chocolatada.crescendo.ui.launch.LaunchScreen
import com.chocolatada.crescendo.ui.main.MainScreen
import com.chocolatada.crescendo.ui.main.MainViewModel

@Composable
fun Navigation(appHasPermissions: () -> Boolean, requestPermissions: () -> Unit){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Launch) {
        composable<Launch> {
            LaunchScreen {
                when(appHasPermissions()) {
                    true -> navController.navigate(Main)
                    false -> requestPermissions()
                }
            }
        }
        composable<Main> {
            // TODO: Implement Dagger Hilt for dependency injection
            val mainViewModel: MainViewModel = viewModel()
            MainScreen(mainViewModel) {
                navController.popBackStack()
            }
        }
    }
}