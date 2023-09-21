package dev.amaro.atairu.core.render

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AtairuEngine {
    fun placeScreen(request: EngineRequest) {
        GlobalScope.launch {
            val screen = request.service.getScreen(request.screenId)
            request.target.processScreen(screen)
        }
    }
}