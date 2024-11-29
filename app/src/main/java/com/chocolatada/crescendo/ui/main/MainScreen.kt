package com.chocolatada.crescendo.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(mainViewModel: MainViewModel){
    val mediaPlayersAreLoaded by mainViewModel.mediaPlayersAreLoaded.collectAsState()
    Column {
        when(mediaPlayersAreLoaded) {
            true -> Text(text = "MediaPlayers loaded!")
            false -> Text(text = "Loading MediaPlayers . . .")
        }
    }
}