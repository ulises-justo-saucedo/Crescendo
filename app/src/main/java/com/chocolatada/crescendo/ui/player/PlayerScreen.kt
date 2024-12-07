package com.chocolatada.crescendo.ui.player

import android.content.ContentUris
import android.media.MediaPlayer
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.chocolatada.crescendo.R
import com.chocolatada.crescendo.audio.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun PlayerScreen(
    song: Song,
    onNavigateToMain: () -> Unit
) {
    var userIsInThisScreen by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(
            context,
            ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, song.id)
        )
    }
    var songStarted by remember { mutableStateOf(true) }
    var songStopped by remember { mutableStateOf(false) }
    var currentSongTimer by remember { mutableFloatStateOf(0f) }
    val songDurationInSeconds = song.duration / 1000
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(context.getColor(R.color.brown)))
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bc_launch),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        MyFloatingActionButton(songStarted) {
            songStarted = !songStarted
            songStopped = !songStopped
        }
        Row {
            Text(text = "${currentSongTimer.toInt()}")
            MyLinearProgressIndicator(
                songDurationInSeconds = songDurationInSeconds,
                currentSongTimer = currentSongTimer
            )
            Text(text = "$songDurationInSeconds")
        }
    }
    BackHandler {
        userIsInThisScreen = false
        songStarted = false
        onNavigateToMain()
    }
    // thread related to linearprogression's modifier
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            while (userIsInThisScreen) {
                while (userIsInThisScreen && songStarted && currentSongTimer < songDurationInSeconds) {
                    delay(1000)
                    currentSongTimer += 1f
                }
                if (currentSongTimer >= songDurationInSeconds) {
                    songStarted = false
                    songStopped = true
                    mediaPlayer.seekTo(0)
                }
                if (currentSongTimer >= songDurationInSeconds && songStarted) {
                    currentSongTimer = 0f
                }
            }
        }
    }
    // and this thread is related to mediaplayer playback management
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            mediaPlayer.start()
            var canExecute = true
            while (userIsInThisScreen) {
                while (songStarted) {
                    if (canExecute) {
                        mediaPlayer.start()
                        canExecute = false
                    }
                }
                canExecute = true
                while (songStopped) {
                    if (canExecute) {
                        mediaPlayer.pause()
                        canExecute = false
                    }
                }
                canExecute = true
            }
            mediaPlayer.release()
        }
    }
}

@Composable
fun MyFloatingActionButton(songStarted: Boolean, onClick: () -> Unit) {
    val imageVectorPlay = ImageVector.vectorResource(id = R.drawable.play)
    val imageVectorPause = ImageVector.vectorResource(id = R.drawable.pause)
    FloatingActionButton(
        shape = CircleShape,
        containerColor = Color.White,
        modifier = Modifier.padding(bottom = 15.dp),
        onClick = onClick
    ) {
        Image(
            imageVector = if(songStarted) imageVectorPause else imageVectorPlay,
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
    }
}

@Composable
fun MyLinearProgressIndicator(
    songDurationInSeconds: Long,
    currentSongTimer: Float
) {
    LinearProgressIndicator(
        progress = { currentSongTimer / songDurationInSeconds },
        modifier = Modifier.height(15.dp)
    )
}