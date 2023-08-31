package dev.amaro.atairu.schema

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


data class MosaicTextStyle(
    val color: Color,
    val fontSize: Int,
    val boolean: Boolean
) {
    companion object {
        fun from(
            jsonObject: JsonElement,
            palette: MosaicPalette,
            fontSizes: MosaicSizes
        ): MosaicTextStyle {
            return MosaicTextStyle(
                jsonObject.jsonObject["color"]?.jsonPrimitive?.content?.toColor(palette)
                    ?: Color.Transparent,
                jsonObject.jsonObject["fontSize"]?.jsonPrimitive?.content?.toSize(fontSizes) ?: 0,
                jsonObject.jsonObject["bold"]?.jsonPrimitive?.content?.toBoolean() ?: false
            )
        }
    }
}
