package dev.amaro.atairu.translators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.amaro.atairu.schema.MosaicTheme
import dev.amaro.atairu.schema.toColor
import dev.amaro.atairu.ui.theme.Shapes
import dev.amaro.atairu.ui.theme.Typography
import iif
import org.json.JSONObject
import toDimension


class Artisan(private var feedContent: JSONObject? = null) {

    private fun parseComponent(component: JSONObject, theme: MosaicTheme): @Composable () -> Unit =
        @Composable {
            if (component.getString("_type") == "Card") {
                Card(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(10.dp)) {
                        val children = component.getJSONArray("content")
                        (0 until children.length()).map {
                            parseComponent(children.getJSONObject(it), theme)()
                        }
                    }
                }
            } else if (component.getString("_type") == "Text") {
                val role = when (component.optString("role", "Text")) {
                    "Header" -> MaterialTheme.typography.h5
                    "SubHeader" -> MaterialTheme.typography.h6
                    else -> MaterialTheme.typography.body1
                }

                Text(
                    text = component.getString("text"),
                    style = role,
                    color = component.optString("color", "")
                        .takeIf { it.isNotEmpty() }
                        ?.run { toColor(theme.palette) } ?: role.color
                )
            } else if (component.getString("_type") == "Space") {
                val size = component.getString("size").toDimension(theme.sizes)
                Spacer(modifier = Modifier.height(size))
            }

        }

    @Composable
    private fun applyTheme(
        definition: MosaicTheme,
        content: @Composable () -> Unit
    ) {
        val palette: Colors =
            MaterialTheme.colors.copy(
                primary = definition.palette.primary,
                surface = definition.palette.surface,
                background = definition.palette.background
            )

        val typographyTheme = MaterialTheme.typography.copy(
            h5 = definition.typography.header.let {
                Typography.h5.copy(
                    color = it.color,
                    fontWeight = it.boolean.iif(FontWeight.Bold, FontWeight.Normal),
                    fontSize = it.fontSize.sp
                )
            },
            h6 = definition.typography.subHeader.let {
                Typography.h6.copy(
                    color = it.color,
                    fontWeight = it.boolean.iif(FontWeight.Bold, FontWeight.Normal),
                    fontSize = it.fontSize.sp
                )
            },
            body1 = definition.typography.text.let {
                Typography.body1.copy(
                    color = it.color,
                    fontWeight = it.boolean.iif(FontWeight.Bold, FontWeight.Normal),
                    fontSize = it.fontSize.sp
                )
            }
        )


        MaterialTheme(
            colors = palette,
            shapes = Shapes,
            typography = typographyTheme,
            content = content
        )
    }


    @Composable
    fun Draw() {
        feedContent?.run {
            val children = getJSONArray("items")
            val theme = MosaicTheme.from(getJSONObject("theme").toString())
            applyTheme(theme) {
                Column(Modifier.padding(10.dp)) {
                    (0 until children.length()).map {
                        parseComponent(children.getJSONObject(it), theme)()
                    }
                }
            }
        }
    }
}

