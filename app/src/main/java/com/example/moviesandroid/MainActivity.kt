package com.example.moviesandroid

import io.flutter.embedding.android.FlutterActivity;
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesandroid.ui.theme.MoviesAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyButton(
                        onClick = {
                            startActivity(
                                FlutterActivity.createDefaultIntent(this)
                            )
                        },
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick) {
        Text("Launch Flutter!")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesAndroidTheme {
    }
}