package jetbrains.datalorePlot.geom

import jetbrains.datalorePlot.Geom.area
import jetbrains.datalorePlot.Pos
import jetbrains.datalorePlot.Stat.identity
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.geom.AreaAesthetics
import jetbrains.datalorePlot.intern.layer.geom.AreaMapping

@Suppress("ClassName")
class geom_area(
    data: Any? = null,
    stat: StatOptions = identity,
    position: PosOptions = Pos.stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: AreaMapping.() -> Unit = {}

) : AreaAesthetics,
    LayerBase(
        mapping = AreaMapping().apply(mapping).seal(),
        data = data,
        geom = area(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


