package jetbrains.datalorePlot.geom

import jetbrains.datalorePlot.Geom
import jetbrains.datalorePlot.Pos
import jetbrains.datalorePlot.Stat
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.geom.HistogramAesthetics
import jetbrains.datalorePlot.intern.layer.geom.HistogramMapping
import jetbrains.datalorePlot.intern.layer.stat.BinAesthetics

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


