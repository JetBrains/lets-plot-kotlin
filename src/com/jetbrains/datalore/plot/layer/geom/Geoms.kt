package com.jetbrains.datalore.plot.layer.geom

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.GeomOptions

val blank = object : GeomOptions() {
    override val kind = GeomKind.BLANK
    override val mapping = Options.empty()
    override val constants = Options.empty()
}

internal object Geoms {
    class Point(
        override val mapping: Options,
        override val constants: Options
    ) : GeomOptions() {
        override val kind = GeomKind.POINT
    }

    class Area(
        override val mapping: Options = Options.empty(),
        override val constants: Options = Options.empty()
    ) : GeomOptions() {
        override val kind = GeomKind.AREA
    }
}

