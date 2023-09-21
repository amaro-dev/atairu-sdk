package dev.amaro.atairu.core.render

import dev.amaro.atairu.core.models.Screen

interface RenderingCanvas {
    fun processScreen(screen: Screen)

}