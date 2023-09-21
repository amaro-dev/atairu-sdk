package dev.amaro.atairu.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Text")
class Text(
    val value: String,
    val size: TextSize = TextSize.REGULAR,
    val color: Color? = null
) : Component {
    override val __typename: String = "Text"
}