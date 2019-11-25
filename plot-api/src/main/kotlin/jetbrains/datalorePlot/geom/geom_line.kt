package jetbrains.datalorePlot.geom

import jetbrains.datalorePlot.Geom
import jetbrains.datalorePlot.Pos.identity
import jetbrains.datalorePlot.Stat
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.geom.*

@Suppress("ClassName")
class geom_line(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: LineMapping.() -> Unit = {}

) : LineAesthetics,
    LayerBase(
        mapping = LineMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.line(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

