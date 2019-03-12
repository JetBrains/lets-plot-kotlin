package com.jetbrains.datalore.plot.geom

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options


class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty(),
    val constants: Options = Options.empty()
)



