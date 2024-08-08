package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.band
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.BandAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BandMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

class geomBand(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val xmin: Number? = null,
    override val xmax: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BandMapping.() -> Unit = {}
) : BandAesthetics,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BandMapping().apply(mapping).seal(),
        data = data,
        geom = band(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal() = super<BandAesthetics>.seal() +
                          super<WithColorOption>.seal() +
                          super<WithFillOption>.seal()
}