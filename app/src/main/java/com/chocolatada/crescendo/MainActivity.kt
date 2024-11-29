package com.chocolatada.crescendo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chocolatada.crescendo.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation(
                appHasPermissions@ {
                    return@appHasPermissions appHasPermissions()
                },
                requestPermissions@ {
                    return@requestPermissions requestExternalStoragePermissions()
                }
            )
        }
    }

    private fun appHasPermissions(): Boolean {
        if(Build.VERSION.SDK_INT >= 33){
            val audioPermission = this.checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO)
            return audioPermission == PackageManager.PERMISSION_GRANTED
        }

        val permission = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestExternalStoragePermissions() {
        if(Build.VERSION.SDK_INT >= 33){
            this.requestPermissions(
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                1
            )
        } else {
            this.requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
    }
}
