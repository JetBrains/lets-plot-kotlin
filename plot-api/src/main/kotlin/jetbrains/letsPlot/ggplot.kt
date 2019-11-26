package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.intern.Plot

fun ggplot(data: Any? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        GenericAesMapping().apply(mapping).seal(),
        emptyList()
    )
}

