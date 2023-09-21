package dev.amaro.atairu.core.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


@Serializable(with = ColorSerializerModule::class)
sealed interface Color : Component {
    val alpha: Short
}

@Serializable
@SerialName("ThemeColor")
class ThemeColor(override val alpha: Short) : Color {
    override val __typename: String = "ThemeColor"
}

@Serializable
@SerialName("HexColor")
class HexColor(override val alpha: Short, val color: String) : Color {
    override val __typename: String = "HexColor"
}

object ColorSerializerModule : JsonContentPolymorphicSerializer<Color>(Color::class) {
    override fun selectDeserializer(element: JsonElement): KSerializer<out Color> {
        return when (element.jsonObject["__typename"]?.jsonPrimitive?.content) {
            "ThemeColor" -> ThemeColor.serializer()
            "HexColor" -> HexColor.serializer()
            else -> throw IllegalArgumentException("Unknown type ${element.jsonObject["__typename"]}")
        }
    }
}