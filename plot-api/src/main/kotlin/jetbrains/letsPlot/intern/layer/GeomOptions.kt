package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options


open class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}



