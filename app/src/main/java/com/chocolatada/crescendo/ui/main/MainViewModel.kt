package com.chocolatada.crescendo.ui.main

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatada.crescendo.audio.MP3PathReader
import com.chocolatada.crescendo.audio.MediaPlayerCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
): ViewModel() {
    private val _mediaPlayerState = MutableStateFlow(MediaPlayerState())
    val mediaPlayerState: StateFlow<MediaPlayerState> = _mediaPlayerState

    init {
        viewModelScope.launch {
            delay(2000L)
            val mp3Paths = MP3PathReader.getAllMP3FilesPath(application.applicationContext)
            val mediaPlayers = MediaPlayerCreator.convertUriToMediaPlayer(
                application.applicationContext,
                mp3Paths
            )
            _mediaPlayerState.value = MediaPlayerState(true, mediaPlayers)
        }
    }
}