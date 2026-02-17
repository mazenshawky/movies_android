package com.example.moviesandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviesandroid.ui.HomeScreen
import com.example.moviesandroid.ui.theme.MoviesAndroidTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngineCache

class MainActivity : ComponentActivity() {

    private var flutterBridge: FlutterBridge? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Wire the MethodChannel bridge at the Activity layer
        FlutterEngineCache.getInstance().get(MoviesApp.ENGINE_ID)?.let { engine ->
            flutterBridge = FlutterBridge(applicationContext, engine)
        }

        setContent {
            MoviesAndroidTheme {
                HomeScreen(
                    onBrowseMovies = {
                        startActivity(
                            FlutterActivity
                                .withCachedEngine(MoviesApp.ENGINE_ID)
                                .build(this@MainActivity)
                        )
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        flutterBridge?.dispose()
        super.onDestroy()
    }
}