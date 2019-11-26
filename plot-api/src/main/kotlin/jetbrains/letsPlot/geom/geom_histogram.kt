package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import jetbrains.letsPlot.intern.layer.stat.BinAesthetics

@Suppress("ClassName", "unused")
class geom_histogram(
    data: Any? = null,
    stat: StatOptions = Stat.bin(),
    position: PosOptions = Pos.stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Double? = null,
    override val weight: Any? = null,
    mapping: HistogramMapping.() -> Unit = {}

) : HistogramAesthetics,
    BinAesthetics,
    LayerBase(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.histogram(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinAesthetics>.seal()
}


