package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.bracket
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.BracketAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BracketMapping
import org.jetbrains.letsPlot.intern.layer.geom.BracketParameters
import org.jetbrains.letsPlot.intern.layer.geom.TextParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

/**
 * TODO
 */
class geomBracket(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = false,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val xmin: Any? = null,
    override val xmax: Any? = null,
    override val y: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    override val x: Any? = null,
    override val lenStart: Any? = null,
    override val lenEnd: Any? = null,
    override val label: Any? = null,
    override val size: Any? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val alpha: Any? = null,
    override val family: Any? = null,
    override val fontface: Any? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Any? = null,
    override val lineheight: Any? = null,
    override val segmentColor: Any? = null,
    override val segmentSize: Any? = null,
    override val segmentAlpha: Any? = null,
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val nudgeX: Number? = null,
    override val nudgeY: Number? = null,
    override val nudgeUnit: String? = null,
    override val checkOverlap: Boolean? = null,
    override val bracketShorten: Number? = null,
    override val tipLengthUnit: String? = null,
    override val sizeUnit: String? = null,
    override val colorBy: String? = null,
    mapping: BracketMapping.() -> Unit = {}
) : BracketAesthetics,
    BracketParameters,
    TextParameters,
    WithSizeUnitOption,
    WithColorOption,
    Layer(
        mapping = BracketMapping().apply(mapping).seal(),
        data = data,
        geom = bracket(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<BracketAesthetics>.seal() +
                super<BracketParameters>.seal() +
                super<TextParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal()
    }
}