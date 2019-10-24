package jetbrains.datalorePlot.intern.layer

import jetbrains.datalorePlot.intern.GeomKind
import jetbrains.datalorePlot.intern.Options


open class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}



