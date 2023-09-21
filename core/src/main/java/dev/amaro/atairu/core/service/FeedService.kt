package dev.amaro.atairu.core.service

import dev.amaro.atairu.core.models.Screen
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface FeedService {
    suspend fun getScreen(screenId: String): Screen
}

class AtairuServerFeedService(private val serviceAddress: String) : FeedService {
    private val jsonEngine = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    private val client = HttpClient()
    override suspend fun getScreen(screenId: String): Screen {
        val response = client.get(serviceAddress).bodyAsText()
        return jsonEngine.decodeFromString(response)
    }
}