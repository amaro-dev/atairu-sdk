package dev.amaro.atairu.schema

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject


data class MosaicTypography(
    val header: MosaicTextStyle,
    val subHeader: MosaicTextStyle,
    val text: MosaicTextStyle
) {
    companion object {
        fun from(
            jsonObject: JsonElement,
            palette: MosaicPalette,
            fontSizes: MosaicSizes
        ): MosaicTypography {
            val header = MosaicTextStyle.from(jsonObject.jsonObject["header"]!!, palette, fontSizes)
            val subHeader =
                MosaicTextStyle.from(jsonObject.jsonObject["subHeader"]!!, palette, fontSizes)
            val text = MosaicTextStyle.from(jsonObject.jsonObject["text"]!!, palette, fontSizes)
            return MosaicTypography(header, subHeader, text)
        }
    }
}
