package dev.amaro.atairu.core.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = ComponentsSerializerModule::class)
interface Component {
    val __typename: String
}


interface Container : Component {
    val items: List<Component>
}

object ComponentsSerializerModule : JsonContentPolymorphicSerializer<Component>(Component::class) {
    override fun selectDeserializer(element: JsonElement): KSerializer<out Component> {
        return when (element.jsonObject["__typename"]?.jsonPrimitive?.content) {
            "Screen" -> Screen.serializer()
            "Text" -> Text.serializer()
            "Card" -> Card.serializer()
            else -> throw IllegalArgumentException("Unknown type ${element.jsonObject["__typename"]}")
        }
    }
}