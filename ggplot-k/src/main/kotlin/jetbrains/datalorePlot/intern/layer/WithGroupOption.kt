package jetbrains.datalorePlot.intern.layer

import jetbrains.datalorePlot.intern.Options

interface WithGroupOption {
    val group: Any?

    fun group() = Options.of(
        "group" to group
    )
}