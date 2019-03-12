package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.geom.GeomOptions
import com.jetbrains.datalore.plot.geom.blank

class SomeFeature : Layer(
    data = null,
    stat = null,
    position = null,
    show_legend = true,
    sampling = null
) {
    override val geom: GeomOptions
        get() = blank.invoke()
}