package dev.amaro.atairu.ui.compose.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.amaro.atairu.core.models.Screen
import dev.amaro.atairu.ui.compose.ComposeParser

@Composable
fun AtairuScreen(model: Screen) {
    model.run {
        Column(Modifier.padding(10.dp)) {
            items.map {
                ComposeParser(it)
            }
        }
    }
}