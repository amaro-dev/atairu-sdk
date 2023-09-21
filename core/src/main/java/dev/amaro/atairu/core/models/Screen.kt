package dev.amaro.atairu.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("Screen")
class Screen(override val items: List<Component>) : Container {
    override val __typename: String = "Screen"
}