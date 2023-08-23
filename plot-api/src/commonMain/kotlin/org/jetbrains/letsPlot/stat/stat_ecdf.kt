package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.geom.LineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.LineMapping
import org.jetbrains.letsPlot.intern.layer.stat.ECDFStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
class statECDF(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.step(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val n: Int? = null,
    override val pad: Boolean? = true,  // set the default
    override val colorBy: String? = null,
    mapping: LineMapping.() -> Unit = {}
) : LineAesthetics,
    ECDFStatParameters,
    WithColorOption,
    Layer(
        mapping = LineMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.ecdf(),
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<LineAesthetics>.seal() +
                super<ECDFStatParameters>.seal() +
                super<WithColorOption>.seal()
    }
}