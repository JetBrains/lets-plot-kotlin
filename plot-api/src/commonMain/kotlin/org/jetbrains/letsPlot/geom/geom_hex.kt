package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.BinHexMapping
import org.jetbrains.letsPlot.intern.layer.geom.HexAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinHexStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinHexStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

class geomHex(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.binhex(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val width: Number? = null,
    override val height: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Pair<Int, Int>? = null,
    override val binWidth: Pair<Number?, Number?>? = null,
    override val drop: Boolean? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BinHexMapping.() -> Unit = {}
) : HexAesthetics,
    BinHexStatAesthetics,
    BinHexStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BinHexMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.hex(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<HexAesthetics>.seal() +
                super<BinHexStatAesthetics>.seal() +
                super<BinHexStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}