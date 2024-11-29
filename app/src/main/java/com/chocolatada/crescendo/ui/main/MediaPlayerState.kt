package com.chocolatada.crescendo.ui.main

import android.media.MediaPlayer

data class MediaPlayerState(
    val mediaPlayersAreLoaded: Boolean = false,
    val mediaPlayers: List<MediaPlayer> = emptyList()
)
