package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.*

@Suppress("ClassName")
class geom_rect(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val xmin: Double? = null,
    override val xmax: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    override val fill: Any? = null,
    mapping: RectMapping.() -> Unit = {}

) : RectAesthetics,
    LayerBase(
        mapping = RectMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.rect(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

