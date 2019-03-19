package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options


open class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}



