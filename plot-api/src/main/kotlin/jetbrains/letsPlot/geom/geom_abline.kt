package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.abline
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.ABLineAesthetics
import jetbrains.letsPlot.intern.layer.geom.ABLineMapping

@Suppress("ClassName")
class geom_abline(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val slope: Double? = null,
    override val intercept: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: ABLineMapping.() -> Unit = {}

) : ABLineAesthetics,
    LayerBase(
        mapping = ABLineMapping().apply(mapping).seal(),
        data = data,
        geom = abline(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

