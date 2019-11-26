package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.point
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.PointAesthetics
import jetbrains.letsPlot.intern.layer.geom.PointMapping

@Suppress("ClassName")
class geom_point(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Double? = null,
    override val stroke: Double? = null,
    mapping: PointMapping.() -> Unit = {}

) : PointAesthetics,
    LayerBase(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = point(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


