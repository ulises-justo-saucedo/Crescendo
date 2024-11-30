package com.chocolatada.crescendo.audio

import android.net.Uri

data class Song(
    val contentUri: Uri,
    val name: String,
    val duration: Long,
)
