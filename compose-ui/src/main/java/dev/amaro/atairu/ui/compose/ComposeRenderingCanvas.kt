package dev.amaro.atairu.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.amaro.atairu.core.models.Screen
import dev.amaro.atairu.core.render.RenderingCanvas
import dev.amaro.atairu.ui.compose.elements.AtairuScreen

class ComposeRenderingCanvas : RenderingCanvas {

    private val nextScreen = mutableStateOf(null as Screen?)

    @Composable
    fun Render() {
        remember { nextScreen }
        nextScreen.value?.apply { AtairuScreen(this) }
    }

    override fun processScreen(screen: Screen) {
        nextScreen.value = screen
    }
}