package com.jetbrains.datalore.plot

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