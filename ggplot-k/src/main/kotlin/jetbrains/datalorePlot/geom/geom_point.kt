package jetbrains.datalorePlot.geom

import jetbrains.datalorePlot.Geom.point
import jetbrains.datalorePlot.Pos.identity
import jetbrains.datalorePlot.Stat
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.geom.PointAesthetics
import jetbrains.datalorePlot.intern.layer.geom.PointMapping

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


