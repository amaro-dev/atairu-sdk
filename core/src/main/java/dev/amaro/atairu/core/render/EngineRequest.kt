package dev.amaro.atairu.core.render

import dev.amaro.atairu.core.service.FeedService

class EngineRequest(
    val screenId: String,
    val target: RenderingCanvas,
    val service: FeedService
)