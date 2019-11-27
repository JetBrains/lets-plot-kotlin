package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.hline
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.HLineAesthetics
import jetbrains.letsPlot.intern.layer.geom.HLineMapping

@Suppress("ClassName")
class geom_hline(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val yintercept: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: HLineMapping.() -> Unit = {}

) : HLineAesthetics,
    LayerBase(
        mapping = HLineMapping().apply(mapping).seal(),
        data = data,
        geom = hline(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

