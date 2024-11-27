package com.chocolatada.crescendo.ui.main

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(onClick: () -> Unit){
    Button(onClick = onClick) {
        Text(text = "Return to Launch Activity ! ! !")
    }
}