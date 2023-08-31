package dev.amaro.atairu.service

import android.content.res.AssetManager
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.json.JSONObject


interface FeedService {
    suspend fun getFeed(): JSONObject
}

class LocalFeedService(private val assets: AssetManager) : FeedService {
    override suspend fun getFeed(): JSONObject {
        return JSONObject(
            assets.open("atairu-screen.json", AssetManager.ACCESS_STREAMING)
                .bufferedReader()
                .readText()
        )
    }
}

const val DESKTOP_IP = "192.168.50.23"

class RemoteFeedService() : FeedService {
    private val client = HttpClient()
    override suspend fun getFeed(): JSONObject {
        return JSONObject(client.get("http://$DESKTOP_IP:8001/feed").bodyAsText())
    }
}

class AtairuFeedService(private val assets: AssetManager) {
    fun getFeed(): Screen {
        val content = assets.open("atairu-screen.json", AssetManager.ACCESS_STREAMING)
            .bufferedReader()
            .readText()
        return jsonEngine.decodeFromString(content)
    }
}

@Serializable(with = ComponentsSerializerModule::class)
interface Component {
    val type: String
}

interface Container : Component {
    val items: List<Component>
}

@Serializable
@SerialName("Card")
class Card(override val items: List<Component> = emptyList()) : Container {
    override val type: String
        get() = "Card"
}

@Serializable
@SerialName("Text")
class Text(val value: String) : Component {
    override val type: String
        get() = "Text"
}

interface Color : Component {
    val alpha: Short
}

@Serializable
class ThemeColor(override val alpha: Short) : Color {
    override val type: String
        get() = "ThemeColor"
}

@Serializable
class HexColor(override val alpha: Short, val color: String) : Color {
    override val type: String
        get() = "HexColor"
}

@Serializable
@SerialName("Screen")
class Screen(override val items: List<Component>) : Container {
    override val type: String
        get() = "Screen"
}


val sampleJson = """
        {
            "type": "Screen",
            "items": [ 
                {
                    "type": "Text",
                    "value": "Hello World"
                }
            ]
        }
    """.trimIndent()

object ComponentsSerializerModule : JsonContentPolymorphicSerializer<Component>(Component::class) {
    override fun selectDeserializer(element: JsonElement): KSerializer<out Component> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "ThemeColor" -> ThemeColor.serializer()
            "HexColor" -> HexColor.serializer()
            "Screen" -> Screen.serializer()
            "Text" -> Text.serializer()
            "Card" -> Card.serializer()
            else -> throw IllegalArgumentException("Unknown type ${element.jsonObject["type"]}")
        }
    }
}


val jsonEngine = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

fun main() {
    val screen = jsonEngine.decodeFromString<Screen>(sampleJson)
    println(screen)
}