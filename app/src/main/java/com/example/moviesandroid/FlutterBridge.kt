package com.example.moviesandroid

import android.content.Context
import android.content.Intent
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

/**
 * Encapsulates the MethodChannel communication between Flutter and native Android.
 *
 * Mirrors the iOS approach where the channel handler directly triggers
 * navigation to the trailer screen. Initialized at the Activity layer,
 * not inside composables.
 */
class FlutterBridge(
    private val context: Context,
    flutterEngine: FlutterEngine
) {
    private val channel = MethodChannel(
        flutterEngine.dartExecutor.binaryMessenger,
        "com.movies/trailer"
    )

    init {
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "playTrailer" -> {
                    val youtubeKey = call.arguments as? String
                    if (youtubeKey != null) {
                        val intent = Intent(context, TrailerActivity::class.java).apply {
                            putExtra(TrailerActivity.EXTRA_YOUTUBE_KEY, youtubeKey)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                        result.success(null)
                    } else {
                        result.error("INVALID_ARG", "YouTube key is null", null)
                    }
                }
                else -> result.notImplemented()
            }
        }
    }

    fun dispose() {
        channel.setMethodCallHandler(null)
    }
}
