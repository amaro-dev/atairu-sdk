package dev.amaro.atairu.core.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable(with = TextSizeSerializerModule::class)
sealed class TextSize {
    object SMALL : TextSize()
    object REGULAR : TextSize()
    object LARGE : TextSize()
}

object TextSizeSerializerModule : KSerializer<TextSize> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("textSize", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TextSize {
        return when (decoder.decodeString()) {
            "SMALL" -> TextSize.SMALL
            "LARGE" -> TextSize.LARGE
            else -> TextSize.REGULAR
        }
    }

    override fun serialize(encoder: Encoder, value: TextSize) {
        encoder.encodeString(value.javaClass.simpleName)
    }

}
