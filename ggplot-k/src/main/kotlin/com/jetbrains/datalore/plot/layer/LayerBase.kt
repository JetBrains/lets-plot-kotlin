package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

abstract class LayerBase(
    mapping: Options,
    data: Any? = null,
    geom: GeomOptions,
    stat: StatOptions,
    position: PosOptions,
    show_legend: Boolean = true,
    sampling: Any? = null
) : Layer(
    mapping = mapping,
    data = data,
    geom = geom,
    stat = stat,
    position = position,
    show_legend = show_legend,
    sampling = sampling
), OptionsCapsule {

    override val parameters by lazy { geom.parameters + stat.parameters + this.seal() }
}