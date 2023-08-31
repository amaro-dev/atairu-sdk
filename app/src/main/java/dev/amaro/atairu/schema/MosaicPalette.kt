package dev.amaro.atairu.schema

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


data class MosaicPalette(
    val primary: Color,
    val surface: Color,
    val background: Color
) {
    companion object {

        fun from(jsonObject: JsonElement): MosaicPalette {
            return MosaicPalette(
                jsonObject.jsonObject["primary"]?.jsonPrimitive?.content?.toColor()
                    ?: Color.Transparent,
                jsonObject.jsonObject["surface"]?.jsonPrimitive?.content?.toColor()
                    ?: Color.Transparent,
                jsonObject.jsonObject["background"]?.jsonPrimitive?.content?.toColor()
                    ?: Color.Transparent
            )
        }
    }
}

fun String?.toColor(palette: MosaicPalette? = null): Color {
    return if (this != null && startsWith("#"))
        Color(substring(1).toLong(16) or 0xFF000000)
    else if (palette != null)
        when (this) {
            "Primary" -> palette.primary
            "Surface" -> palette.surface
            "Background" -> palette.background
            else -> Color.Transparent
        }
    else Color.Transparent
}