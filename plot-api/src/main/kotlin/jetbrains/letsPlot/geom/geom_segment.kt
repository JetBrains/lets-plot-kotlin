package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.segment
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.SegmentAesthetics
import jetbrains.letsPlot.intern.layer.geom.SegmentMapping

@Suppress("ClassName")
class geom_segment(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val xend: Double? = null,
    override val yend: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    mapping: SegmentMapping.() -> Unit = {}

) : SegmentAesthetics,
    LayerBase(
        mapping = SegmentMapping().apply(mapping).seal(),
        data = data,
        geom = segment(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )