package com.chocolatada.crescendo.audio

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MP3PathReader {
    companion object {
        suspend fun getAllMP3FilesPath(context: Context): List<Uri> {
            val mp3Paths = mutableListOf<Uri>()

            withContext(Dispatchers.IO) {
                context.contentResolver.query(
                    MediaStore.Files.getContentUri("external"),
                    arrayOf(MediaStore.Files.FileColumns.DATA),
                    null,
                    null,
                    null
                )?.use {
                    MediaStore.Files.FileColumns.DATA
                    val index = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                    while(it.moveToNext()) {
                        mp3Paths.add(it.getString(index).toUri())
                    }
                }
            }

            return mp3Paths
        }
    }
}