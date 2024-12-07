package com.chocolatada.crescendo.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.chocolatada.crescendo.audio.Song
import com.chocolatada.crescendo.ui.launch.LaunchScreen
import com.chocolatada.crescendo.ui.main.MainScreen
import com.chocolatada.crescendo.ui.main.MainViewModel
import com.chocolatada.crescendo.ui.player.PlayerScreen

@Composable
fun Navigation(appHasPermissions: () -> Boolean, requestPermissions: () -> Unit){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Launch) {
        composable<Launch> {
            LaunchScreen {
                when(appHasPermissions()) {
                    true -> navController.navigate(Main) {
                        popUpTo<Launch> { inclusive = true }
                    }
                    false -> requestPermissions()
                }
            }
        }
        composable<Main> {
            val mainViewModel: MainViewModel = hiltViewModel()
            MainScreen(mainViewModel) {
                id, name, duration -> navController.navigate(Player(id = id, name = name, duration = duration))
            }
        }
        composable<Player> {
            val song: Song = it.toRoute()
            PlayerScreen(song = song) {
                navController.navigate(Main) {
                    popUpTo<Main> { inclusive = true }
                }
            }
        }
    }
}