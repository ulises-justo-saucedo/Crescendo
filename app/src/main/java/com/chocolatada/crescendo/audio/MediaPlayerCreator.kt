package com.chocolatada.crescendo.audio

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaPlayerCreator {
    companion object {
        suspend fun convertUriToMediaPlayer(context: Context, mp3Paths: List<Uri>): List<MediaPlayer> {
            val mediaPlayers = mutableListOf<MediaPlayer>()

            withContext(Dispatchers.IO) {
                for(mp3Path in mp3Paths){
                    mediaPlayers.add(MediaPlayer.create(context, mp3Path))
                }
            }

            return mediaPlayers
        }
    }
}