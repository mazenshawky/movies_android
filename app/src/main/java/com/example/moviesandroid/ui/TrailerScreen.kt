package com.example.moviesandroid.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.safeDrawingPadding

/**
 * Displays a YouTube trailer by loading `youtube_relay.html` from assets.
 *
 * Uses the same relay-HTML technique as the iOS TrailerViewController:
 * the `{{VIDEO_ID}}` placeholder is replaced at runtime and the page is
 * loaded with a youtube-nocookie.com base URL so the Referer header
 * satisfies YouTube's embed requirements.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TrailerScreen(
    youtubeKey: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Read the relay HTML once and inject the video ID
    val htmlContent = remember(youtubeKey) {
        context.assets.open("youtube_relay.html")
            .bufferedReader()
            .readText()
            .replace("{{VIDEO_ID}}", youtubeKey)
    }

    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .safeDrawingPadding(),
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(android.graphics.Color.BLACK)

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                }

                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()

                // Load with youtube-nocookie base URL for correct Referer header
                loadDataWithBaseURL(
                    "https://www.youtube-nocookie.com",
                    htmlContent,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        onRelease = { webView ->
            webView.destroy()
        }
    )
}
