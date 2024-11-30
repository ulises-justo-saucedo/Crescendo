package com.chocolatada.crescendo.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatada.crescendo.audio.MP3Reader
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {
    private val _songState = MutableStateFlow(SongState())
    val songState: StateFlow<SongState> = _songState

    init {
        viewModelScope.launch {
            val songs = MP3Reader.getAllSongsFromStorage(context)
            _songState.value = SongState(true, songs)
        }
    }
}