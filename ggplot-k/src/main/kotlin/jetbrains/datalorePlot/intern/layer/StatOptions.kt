package jetbrains.datalorePlot.intern.layer

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.StatKind

open class StatOptions(
    val kind: StatKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}