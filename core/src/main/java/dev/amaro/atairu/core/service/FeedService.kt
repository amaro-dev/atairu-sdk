package dev.amaro.atairu.core.service

import dev.amaro.atairu.core.models.Screen
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface FeedService {
    suspend fun getScreen(): Screen
}

class AtairuServerFeedService() : FeedService {
    private val jsonEngine = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val client = HttpClient()
    override suspend fun getScreen(): Screen {
        val response =
            client.get("https://atairu-7jyxifti8-amaro-dev.vercel.app/api/mobile").bodyAsText()
        return jsonEngine.decodeFromString(response)
    }
}