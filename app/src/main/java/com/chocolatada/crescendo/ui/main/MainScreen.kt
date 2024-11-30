package com.chocolatada.crescendo.ui.main

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(mainViewModel: MainViewModel){
    val songState by mainViewModel.songState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    if(songState.mediaPlayersAreLoaded) {
        LazyColumn {
            items(songState.songs.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = songState.songs[index].name, color = Color.Black)
                    Button(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                val contentUri = songState.songs[index].contentUri
                                val mediaPlayer = MediaPlayer.create(context, contentUri)
                                val songDuration = songState.songs[index].duration
                                mediaPlayer.start()
                                delay(songDuration)
                                mediaPlayer.release()
                            }
                        }
                    ) {
                        Text(text = "Play this song!", color = Color.Black)
                    }
                }
            }
        }
    } else {
        Text(text = "Loading MediaPlayers . . .")
    }
}