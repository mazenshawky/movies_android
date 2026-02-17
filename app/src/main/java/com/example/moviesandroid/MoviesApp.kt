package com.example.moviesandroid

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MoviesApp : Application() {

    companion object {
        const val ENGINE_ID = "movies_engine"
    }

    override fun onCreate() {
        super.onCreate()
        // Pre-warm the Flutter engine so the movies screen loads instantly
        val flutterEngine = FlutterEngine(this).apply {
            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        }
        FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
    }
}
