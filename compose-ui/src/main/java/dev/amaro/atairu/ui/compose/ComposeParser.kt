package dev.amaro.atairu.ui.compose

import androidx.compose.runtime.Composable
import dev.amaro.atairu.core.models.Card
import dev.amaro.atairu.core.models.Component
import dev.amaro.atairu.core.models.Text
import dev.amaro.atairu.ui.compose.elements.AtairuCard
import dev.amaro.atairu.ui.compose.elements.AtairuText

@Composable
fun ComposeParser(model: Component) {
    when (model) {
        is Card -> AtairuCard(model)
        is Text -> AtairuText(model)
    }
}