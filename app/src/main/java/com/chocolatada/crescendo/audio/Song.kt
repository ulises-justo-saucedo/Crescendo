package com.chocolatada.crescendo.audio

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val id: Long,
    val name: String,
    val duration: Long,
)
