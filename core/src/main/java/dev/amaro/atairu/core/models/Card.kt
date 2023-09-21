package dev.amaro.atairu.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Card")
class Card(
    override val items: List<Component> = emptyList(),
    val background: Color? = null
) : Container {
    override val __typename: String = "Card"
}