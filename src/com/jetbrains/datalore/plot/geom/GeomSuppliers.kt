package com.jetbrains.datalore.plot.geom

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options

internal typealias GeomSupplier = () -> GeomOptions

val blank: GeomSupplier = { GeomOptions(GeomKind.BLANK) }

internal object GeomSuppliers {
    internal fun point(
        mapping: Options,
        constants: Options
    ) = {
        GeomOptions(
            GeomKind.POINT,
            mapping = mapping,
            constants = constants
        )
    }
}

