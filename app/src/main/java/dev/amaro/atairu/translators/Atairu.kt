package dev.amaro.atairu.translators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.amaro.atairu.service.Card
import dev.amaro.atairu.service.Component
import dev.amaro.atairu.service.Screen
import dev.amaro.atairu.service.Text

class AtairuScreen(private var screenDef: Screen) {

    private fun parseComponent(component: Component): @Composable () -> Unit = @Composable {
        if (component is Card) {
            Card(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(10.dp)) {
                    component.items.map {
                        parseComponent(it)()
                    }
                }
            }
        } else if (component is Text) {
            val style = MaterialTheme.typography.body1
            //.copy(fontSize = component.getString("size").toFontSize())

            Text(
                text = component.value,
                style = style,
                // color =   component.optString("color", "").takeIf { it.isNotEmpty() }?.run { toColor(theme.palette) } ?: style.color
            )
        }
    }

    @Composable
    fun Render() {
        screenDef.run {

            Column(Modifier.padding(10.dp)) {
                items.map {
                    parseComponent(it)()
                }
            }

        }
    }
}


fun String.toFontSize() = when (this) {
    "SMALL" -> 10.sp
    "LARGE" -> 18.sp
    else -> 14.sp
}