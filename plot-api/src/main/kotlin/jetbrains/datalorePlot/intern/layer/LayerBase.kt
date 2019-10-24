package jetbrains.datalorePlot.intern.layer

import jetbrains.datalorePlot.intern.Layer
import jetbrains.datalorePlot.intern.Options

abstract class LayerBase(
    mapping: Options,
    data: Any? = null,
    geom: GeomOptions,
    stat: StatOptions,
    position: PosOptions,
    show_legend: Boolean,
    sampling: Any?
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