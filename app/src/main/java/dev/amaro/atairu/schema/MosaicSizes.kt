package dev.amaro.atairu.schema

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

data class MosaicSizes(
    val smaller: Int,
    val small: Int,
    val regular: Int,
    val big: Int,
    val bigger: Int
) {

    companion object {
        fun from(jsonObject: JsonElement): MosaicSizes {
            val specs = jsonObject.jsonObject["specs"]
            val values = specs!!.jsonObject.keys.asSequence()
                .map { it to specs.jsonObject[it]!!.jsonObject["android"]!!.jsonPrimitive.content }
                .toMap()
            return MosaicSizes(
                values["smaller"]?.toInt() ?: 0,
                values["small"]?.toInt() ?: 0,
                values["regular"]?.toInt() ?: 0,
                values["big"]?.toInt() ?: 0,
                values["bigger"]?.toInt() ?: 0
            )
        }

    }

    fun valueFor(value: String) = when (value) {
        "smaller" -> smaller
        "small" -> small
        "regular" -> regular
        "big" -> big
        "bigger" -> bigger
        else -> 0
    }
}

fun String?.toSize(sizes: MosaicSizes): Int {
    return if (this != null)
        when (this) {
            "smaller" -> sizes.small
            "small" -> sizes.smaller
            "regular" -> sizes.regular
            "big" -> sizes.big
            "bigger" -> sizes.bigger
            else -> 0
        }
    else 0
}
