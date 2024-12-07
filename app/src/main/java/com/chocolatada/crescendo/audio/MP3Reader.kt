package com.chocolatada.crescendo.audio

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MP3Reader {
    companion object {
        suspend fun getAllSongsFromStorage(context: Context): List<Song> {
            val songs = mutableListOf<Song>()

            withContext(Dispatchers.IO) {
                val projection = arrayOf(
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION
                )

                val selection = "${MediaStore.Audio.Media.MIME_TYPE} = ?"

                val selectionArgs = arrayOf("audio/mpeg")

                context.contentResolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    null
                )?.use { cursor ->
                    val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                    val displayNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                    val durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

                    while (cursor.moveToNext()) {
                        val id = cursor.getLong(idIndex)
                        val displayName = cursor.getString(displayNameIndex)
                        val duration = cursor.getLong(durationIndex)

                        val song = Song(id, displayName, duration)

                        songs.add(song)
                    }
                }
            }

            return songs
        }
    }
}