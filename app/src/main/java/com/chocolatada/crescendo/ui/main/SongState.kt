package com.chocolatada.crescendo.ui.main

import com.chocolatada.crescendo.audio.Song

data class SongState(
    val songsAreLoaded: Boolean = false,
    val songs: List<Song> = emptyList()
)
