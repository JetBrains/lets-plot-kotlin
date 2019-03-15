package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options


abstract class GeomOptions {
    abstract val kind: GeomKind
    abstract val mapping: Options
    abstract val parameters: Options
}



