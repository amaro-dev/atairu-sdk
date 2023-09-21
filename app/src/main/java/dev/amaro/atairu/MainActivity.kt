package dev.amaro.atairu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import dev.amaro.atairu.core.AtairuCli
import dev.amaro.atairu.ui.compose.ComposeRenderingCanvas


class MainActivity : ComponentActivity() {
    private val canvas = ComposeRenderingCanvas()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AtairuCli.start()

        setContent {

            Column {
                Button(onClick = { refresh() }) {
                    Text("Refresh")
                }
                LaunchedEffect("Loading") {
                    refresh()
                }
                canvas.Render()
            }
        }
    }

    private fun refresh() {
        AtairuCli.renderScreen(canvas, "")
    }
}
