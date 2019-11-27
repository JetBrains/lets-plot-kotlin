package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.vline
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.VLineAesthetics
import jetbrains.letsPlot.intern.layer.geom.VLineMapping

@Suppress("ClassName")
class geom_vline(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val xintercept: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: VLineMapping.() -> Unit = {}

) : VLineAesthetics,
    LayerBase(
        mapping = VLineMapping().apply(mapping).seal(),
        data = data,
        geom = vline(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

