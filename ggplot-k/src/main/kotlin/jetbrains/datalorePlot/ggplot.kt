package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.GenericAesMapping
import jetbrains.datalorePlot.intern.Plot

fun ggplot(data: Any? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        GenericAesMapping().apply(mapping).seal(),
        emptyList()
    )
}

