package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.path
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import jetbrains.letsPlot.intern.layer.geom.PathMapping

@Suppress("ClassName")
class geom_path(
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
    override val speed: Double? = null,
    override val flow: Double? = null,
    mapping: PathMapping.() -> Unit = {}

) : PathAesthetics,
    LayerBase(
        mapping = PathMapping().apply(mapping).seal(),
        data = data,
        geom = path(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


