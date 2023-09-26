package dev.amaro.atairu.core

import dev.amaro.atairu.core.render.AtairuEngine
import dev.amaro.atairu.core.render.EngineRequest
import dev.amaro.atairu.core.render.RenderingCanvas
import dev.amaro.atairu.core.service.AtairuServerFeedService
import dev.amaro.atairu.core.service.FeedService

object AtairuCli {
    private lateinit var settings: ClientSettings
    private lateinit var service: FeedService
    private val engine = AtairuEngine()
    fun start(settings: ClientSettings = ClientSettings()) {
        this.settings = settings
        service = AtairuServerFeedService(settings.environment)
    }

    fun renderScreen(target: RenderingCanvas, screenId: String) {
        engine.placeScreen(EngineRequest(screenId, target, service))
    }
}