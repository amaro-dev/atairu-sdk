package dev.amaro.atairu.schema

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

data class MosaicTheme(
    val sizes: MosaicSizes,
    val fontSizes: MosaicSizes,
    val palette: MosaicPalette,
    val typography: MosaicTypography
) {
    companion object {
        fun from(jsonString: String): MosaicTheme {
            return from(Json.decodeFromString<JsonElement>(jsonString).jsonObject["def"]!!)
        }

        fun from(jsonObject: JsonElement): MosaicTheme {
            val palette = MosaicPalette.from(jsonObject.jsonObject["palette"]!!)
            val fontSizes =
                MosaicSizes.from(jsonObject.jsonObject["typography"]!!.jsonObject["fontSizes"]!!)
            return MosaicTheme(
                MosaicSizes.from(jsonObject.jsonObject["dimensions"]!!),
                fontSizes,
                palette,
                MosaicTypography.from(jsonObject.jsonObject["typography"]!!, palette, fontSizes)
            )
        }
    }
}