package com.example.moviesandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviesandroid.ui.TrailerScreen
import com.example.moviesandroid.ui.theme.MoviesAndroidTheme

/**
 * Displays a YouTube trailer in a WebView.
 *
 * Launched directly by [FlutterBridge] when Flutter sends a YouTube key
 * via MethodChannel — mirrors the iOS flow where TrailerViewController
 * is pushed from the channel handler.
 */
class TrailerActivity : ComponentActivity() {

    companion object {
        const val EXTRA_YOUTUBE_KEY = "youtube_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val youtubeKey = intent.getStringExtra(EXTRA_YOUTUBE_KEY) ?: run {
            finish()
            return
        }

        setContent {
            MoviesAndroidTheme {
                TrailerScreen(youtubeKey = youtubeKey)
            }
        }
    }
}
