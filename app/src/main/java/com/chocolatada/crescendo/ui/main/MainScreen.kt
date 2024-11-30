package com.chocolatada.crescendo.ui.main

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolatada.crescendo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(mainViewModel: MainViewModel){
    val songState by mainViewModel.songState.collectAsState()
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.bc_main),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(LocalContext.current.getColor(R.color.brown)))
    )
    if(songState.songsAreLoaded) {
        LazyColumn {
            items(songState.songs.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DisplaySongName(songState.songs[index].name)
                    DisplayPlayButton(
                        songState.songs[index].contentUri,
                        context,
                        songState.songs[index].duration
                    )
                }
            }
        }
    } else {
        Text(text = "Loading MP3 files . . .")
    }
}

@Composable
fun DisplaySongName(songName: String) {
    val horizontalScroll = rememberScrollState()

    Box(
        modifier = Modifier
            .size(250.dp, 40.dp)
            .horizontalScroll(horizontalScroll, false)
    ){
        Text(
            text = songName,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.mainfont))
        )
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            var i = 0
            val tween = tween<Float>(durationMillis = 1)
            while(true) {
                while(horizontalScroll.canScrollForward) {
                    i += 3
                    horizontalScroll.animateScrollTo(i, tween)
                }
                delay(1000)
                while(horizontalScroll.canScrollBackward) {
                    i -= 3
                    horizontalScroll.animateScrollTo(i, tween)
                }
                delay(1000)
            }
        }
    }
}

@Composable
fun DisplayPlayButton(contentUri: Uri, context: Context, songDuration: Long) {
    val scope = rememberCoroutineScope()
    FloatingActionButton(
        shape = CircleShape,
        containerColor = Color.White,
        modifier = Modifier
            .size(50.dp),
        onClick = {
            scope.launch(Dispatchers.IO) {
                val mediaPlayer = MediaPlayer.create(context, contentUri)
                mediaPlayer.start()
                delay(songDuration)
                mediaPlayer.release()
            }
        }
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.play),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
    }
}