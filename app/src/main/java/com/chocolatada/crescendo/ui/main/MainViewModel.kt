package com.chocolatada.crescendo.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatada.crescendo.audio.MP3PathReader
import com.chocolatada.crescendo.audio.MediaPlayerCreator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(context: Context): ViewModel() {
    private val _mediaPlayersAreLoaded = MutableStateFlow(false)
    val mediaPlayersAreLoaded: StateFlow<Boolean> = _mediaPlayersAreLoaded

    init {
        viewModelScope.launch {
            val mp3Paths = MP3PathReader.getAllMP3FilesPath(context)
            MediaPlayerCreator.convertUriToMediaPlayer(context, mp3Paths)
            _mediaPlayersAreLoaded.value = true
        }
    }
}