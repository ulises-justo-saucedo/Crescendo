package com.chocolatada.crescendo.navigation

import kotlinx.serialization.Serializable

@Serializable
object Launch

@Serializable
object Main

@Serializable
data class Player(val id: Long, val name: String, val duration: Long)