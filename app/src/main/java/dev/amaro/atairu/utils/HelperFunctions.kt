import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.amaro.atairu.schema.MosaicSizes

fun String.toDimension(sizes: MosaicSizes? = null): Dp {
    val occurrences = "(\\d+)(\\w+)".toRegex().findAll(this).toList()
    if (occurrences.isEmpty())
        return (sizes?.valueFor(this) ?: 0).dp
    val values = occurrences.first().groupValues

    return when (values[2]) {
        "em" -> values[1].toInt() * 16
        else -> values[1].toInt()
    }.dp
}


fun <T> Boolean.iif(ifTrue: T, ifFalse: T): T =
    if (this) ifTrue else ifFalse