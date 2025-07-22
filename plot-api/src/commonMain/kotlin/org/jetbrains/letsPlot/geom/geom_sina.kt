package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.SinaAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.SinaMapping
import org.jetbrains.letsPlot.intern.layer.geom.SinaParameters
import org.jetbrains.letsPlot.intern.layer.stat.SinaStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.SinaStatParameters
import org.jetbrains.letsPlot.pos.positionDodge
import org.jetbrains.letsPlot.tooltips.TooltipOptions

/*
TODO
*/
class geomSina(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.sina(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val violinWidth: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Any? = null,
    override val width: Number? = null,
    override val weight: Number? = null,
    override val scale: String? = null,
    override val tailsCutoff: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val quantiles: List<Number>? = null,
    override val showHalf: Number? = null,
    override val seed: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: SinaMapping.() -> Unit = {}
) : SinaAesthetics,
    SinaParameters,
    SinaStatAesthetics,
    SinaStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = SinaMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.SINA),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal() = super<SinaAesthetics>.seal() +
            super<SinaParameters>.seal() +
            super<SinaStatAesthetics>.seal() +
            super<SinaStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}