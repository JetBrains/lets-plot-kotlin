package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import jetbrains.letsPlot.intern.layer.geom.BarMapping

@Suppress("ClassName")
class geom_bar(
    data: Any? = null,
    stat: StatOptions = Stat.count(),
    position: PosOptions = Pos.stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Double? = null,
    override val size: Double? = null,
    mapping: BarMapping.() -> Unit = {}

) : BarAesthetics,
    LayerBase(
        mapping = BarMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.bar(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


