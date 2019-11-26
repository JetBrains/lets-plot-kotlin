package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options

interface WithGroupOption {
    val group: Any?

    fun group() = Options.of(
        "group" to group
    )
}