package dev.amaro.atairu.ui.compose.elements

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.amaro.atairu.ui.compose.utilities.toFontSize
import dev.amaro.atairu.ui.compose.utilities.toValue
import dev.amaro.atairu.core.models.Text as TextModel

@Composable
fun AtairuText(model: TextModel) {
    val style = MaterialTheme.typography.body1
        .copy(fontSize = model.size.toFontSize())

    Text(
        text = model.value,
        style = style,
        color = model.color?.toValue() ?: style.color
    )
}