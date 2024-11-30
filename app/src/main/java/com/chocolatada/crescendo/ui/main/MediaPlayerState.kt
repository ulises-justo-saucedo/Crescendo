package com.chocolatada.crescendo.ui.main

import com.chocolatada.crescendo.audio.Song

data class MediaPlayerState(
    val mediaPlayersAreLoaded: Boolean = false,
    val songs: List<Song> = emptyList()
)
