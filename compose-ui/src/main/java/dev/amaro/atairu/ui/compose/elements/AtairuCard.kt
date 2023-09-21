package dev.amaro.atairu.ui.compose.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.amaro.atairu.ui.compose.ComposeParser
import dev.amaro.atairu.ui.compose.utilities.toValue
import dev.amaro.atairu.core.models.Card as CardModel

@Composable
fun AtairuCard(model: CardModel) {
    Card(
        backgroundColor = model.background?.toValue() ?: MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(10.dp)) {
            model.items.map {
                ComposeParser(it)
            }
        }
    }

}