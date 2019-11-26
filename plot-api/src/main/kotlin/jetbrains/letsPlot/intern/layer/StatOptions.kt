package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.StatKind

open class StatOptions(
    val kind: StatKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}