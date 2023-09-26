package dev.amaro.atairu.ui.compose.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.amaro.atairu.core.models.HexColor
import dev.amaro.atairu.core.models.TextSize
import dev.amaro.atairu.core.models.ThemeColor
import dev.amaro.atairu.core.models.Color as AtairuColor


fun TextSize.toFontSize() = when (this) {
    TextSize.SMALL -> 10.sp
    TextSize.LARGE -> 18.sp
    else -> 14.sp
}

fun AtairuColor?.toValue() =
    when (this) {
        is HexColor -> Color(color.substring(1).toLong(16) or 0xFF000000).copy(alpha = alpha / 100f)
        is ThemeColor -> Color.White
        null -> Color.LightGray
    }